package com.sharing.cn.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sharing.cn.utils.DateUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.util.MapUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * url
 *
 * @author ext.shikai1
 */
@Slf4j
public class UrlUtils {

    /**
     * 转换url
     *
     * @param url
     * @return
     */
    public static URL toUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            log.error("转换url异常", e);
        }
        return null;
    }

    /**
     * url 获取url 参数转为map
     *
     * @return
     */
    public static Map<String, String> urlRequestToMap(String urlPath) {
        Map<String, String> urlMap = new HashMap<>();
        URL url = toUrl(urlPath);
        if (null == url) {
            return urlMap;
        }
        if (StringUtils.isBlank(url.getQuery())) {
            return urlMap;
        }
        String[] urlSplit = url.getQuery().split("[|&|&&]");
        for (String s : urlSplit) {
            if (s.contains("=")) {
                String[] split = s.split("[=]");
                urlMap.put(split[0], split[1]);
            }
        }
        return urlMap;
    }

    @SneakyThrows
    public static Object urlToObject(String urlPath, Object object) {
        Map<String, String> urlMap = urlRequestToMap(urlPath);
        if (MapUtils.isEmpty(urlMap)) {
            return null;
        }
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String value = urlMap.get(name);
            if (StringUtils.isNotBlank(value)) {
                declaredField.setAccessible(true);
                // 获取所需的属性值
                PropertyDescriptor pd = new PropertyDescriptor(name, object.getClass());
                // 获得写属性值的方法
                Method setMethod = pd.getWriteMethod();
                // 获取属性的数据类型
                String typeConvert = declaredField.getType().getSimpleName();
                if (Byte.TYPE.getName().equals(typeConvert) || "Byte".equals(typeConvert)) {
                    declaredField.set(object, Byte.parseByte(value + ""));
                }
                if (Short.TYPE.getName().equals(typeConvert) || "Short".equals(typeConvert)) {
                    declaredField.set(object, Short.parseShort(value + ""));
                }
                if (Integer.TYPE.getName().equals(typeConvert) || "Integer".equals(typeConvert)) {
                    declaredField.set(object, Integer.parseInt(value));
                }
                if (Float.TYPE.getName().equals(typeConvert) || "Float".equals(typeConvert)) {
                    declaredField.set(object, Float.parseFloat(value));
                }
                if (Long.TYPE.getName().equals(typeConvert) || "Long".equals(typeConvert)) {
                    declaredField.set(object, Long.parseLong(value + ""));
                }
                if (Double.TYPE.getName().equals(typeConvert) || "Double".equals(typeConvert)) {
                    declaredField.set(object, Double.parseDouble(value + ""));
                }
                if ("String".equals(typeConvert)) {
                    declaredField.set(object, value);
                }
                if (Boolean.TYPE.getName().equals(typeConvert) || "Boolean".equals(typeConvert)) {
                    declaredField.set(object, Boolean.parseBoolean(value + ""));
                }
                if ("Date".equals(typeConvert)) {
                    // 根据实际时间类型切换
                    Date d = DateUtils.strToDate(value);
                    declaredField.set(object, d);
                }
            }
        }
        return object;
    }

    public static String dataToUrl(Object object) {
        HashMap<String, Object> hashMap = JSON.parseObject(JSON.toJSONString(object), new TypeReference<HashMap<String, Object>>() {
        });
        StringBuilder stringBuilder = new StringBuilder("?");
        for (String s : hashMap.keySet()) {
            if (null != hashMap.get(s)) {
                stringBuilder.append(s).append("=").append(hashMap.get(s)).append("&");
            }
        }
        return stringBuilder.toString();
    }


}
