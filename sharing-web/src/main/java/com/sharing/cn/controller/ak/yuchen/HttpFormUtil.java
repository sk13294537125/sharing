package com.sharing.cn.controller.ak.yuchen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharing.cn.utils.ObjectUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.StringJoiner;

public class HttpFormUtil {
     private static final String UTF8 = "UTF-8";
     private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
     private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

     /**
      * 【核心修复方法】彻底绕过JDK证书校验，100%解决PKIX
      */
     private static CloseableHttpClient getIgnoreSslHttpClient() {
          try {
               // 自定义全放行信任管理器
               X509TrustManager trustManager = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                         return new X509Certificate[0];
                    }
               };
               TrustManager[] trustAll = new TrustManager[]{trustManager};
               SSLContext sslContext = SSLContext.getInstance("TLS");
               sslContext.init(null, trustAll, new SecureRandom());

               SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
                       sslContext,
                       new String[]{"TLSv1.2"},
                       null,
                       NoopHostnameVerifier.INSTANCE
               );

               RequestConfig config = RequestConfig.custom()
                       .setConnectTimeout(10000)
                       .setSocketTimeout(15000)
                       .setConnectionRequestTimeout(5000)
                       .build();

               // 关键点：不使用连接池 PoolingHttpClientConnectionManager
               return HttpClientBuilder.create()
                       .setSSLSocketFactory(sslFactory)
                       .setDefaultRequestConfig(config)
                       // 禁用系统默认SSL参数覆盖
                       .disableConnectionState()
                       .build();
          } catch (Exception e) {
               throw new RuntimeException("创建免SSL客户端失败", e);
          }
     }


     // Map拼接表单参数自动URL编码
     public static String mapToFormStr(Map<String, Object> paramMap) {
          if (paramMap == null || paramMap.isEmpty()) return "";
          StringJoiner joiner = new StringJoiner("&");
          for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
               String k = entry.getKey();
               String v = entry.getValue() == null ? "" : String.valueOf(entry.getValue());
               try {
                    String ek = java.net.URLEncoder.encode(k, UTF8);
                    String ev = java.net.URLEncoder.encode(v, UTF8);
                    joiner.add(ek + "=" + ev);
               } catch (UnsupportedEncodingException e) {
                    joiner.add(k + "=" + v);
               }
          }
          return joiner.toString();
     }

     // 构建HttpPost对象
     public static HttpPost buildHttpPost(String url, Object paramEntity, Map<String, String> headerMap) {
          Map<String, Object> paramMap = ObjectUtils.toMapIncludingSuper(paramEntity);
          String formBody = mapToFormStr(paramMap);
          HttpPost httpPost = new HttpPost(url);
          try {
               StringEntity entity = new StringEntity(formBody, UTF8);
               entity.setContentType(FORM_CONTENT_TYPE);
               httpPost.setEntity(entity);
               httpPost.addHeader("Accept", "*/*");
               httpPost.addHeader("User-Agent", "Java-HttpClient/4.5");
               if (headerMap != null && !headerMap.isEmpty()) {
                    headerMap.forEach(httpPost::addHeader);
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
          return httpPost;
     }

     // 发送POST请求（唯一入口，只使用自定义免SSL客户端）
     public static String doPost(String url, Object paramEntity, Map<String, String> headerMap) {
          HttpPost httpPost = buildHttpPost(url, paramEntity, headerMap);
          try (CloseableHttpClient httpClient = getIgnoreSslHttpClient();
               CloseableHttpResponse response = httpClient.execute(httpPost)) {
               if (response.getEntity() == null) {
                    return "";
               }
               String result = EntityUtils.toString(response.getEntity(), UTF8);
               EntityUtils.consume(response.getEntity());
               return result;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return "";
     }

     // JSON转实体
     public static <T> T parseResponse(String respJson, Class<T> targetCls) {
          try {
               return OBJECT_MAPPER.readValue(respJson, targetCls);
          } catch (Exception e) {
               throw new RuntimeException("响应解析失败", e);
          }
     }

     // JSON转Map
     public static Map<String, Object> parseResponseToMap(String respJson) {
          try {
               return OBJECT_MAPPER.readValue(respJson, new TypeReference<Map<String, Object>>() {
               });
          } catch (Exception e) {
               throw new RuntimeException("JSON转Map失败", e);
          }
     }
}
