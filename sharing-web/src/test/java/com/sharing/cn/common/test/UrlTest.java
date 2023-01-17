package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.utils.http.UrlUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;

/**
 * @author ext.shikai1
 */
@Slf4j
public class UrlTest {

    @Test
    public void urlObject() throws Exception {
        URL url = new URL("http://www.runoob.com/html/html-tutorial.html?id=1663572874421&oid=O221018123614166496");
        System.out.println("URL 是 " + url.toString()); //http://www.runoob.com/html/html-tutorial.html
        System.out.println("协议是 " + url.getProtocol()); //http
        System.out.println("文件名是 " + url.getFile()); // /html/html-tutorial.html?id=1663572874421&oid=O221018123614166496

        System.out.println("主机是 " + url.getHost()); // www.runoob.com
        System.out.println("路径是 " + url.getPath()); // /html/html-tutorial.html
        System.out.println("端口号是 " + url.getPort()); //  -1
        System.out.println("默认端口号是 " + url.getDefaultPort()); //  80

        System.out.println(url.getQuery());
        String[] split = url.getQuery().split("&");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        String url = "http://www.runoob.com/html/html-tutorial.html?id=16635&createdTime=2022-11-24 00:01:05";
        BaseDataBo baseDataBo = new BaseDataBo();
        UrlUtils.urlToObject(url, baseDataBo);
        log.info(JSON.toJSONString(baseDataBo));
    }

}
