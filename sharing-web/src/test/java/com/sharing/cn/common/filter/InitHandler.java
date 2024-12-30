package com.sharing.cn.common.filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterConfig;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class InitHandler {

    public static void handler(FilterConfig filterConfig, Set<String> encryptCacheUri, AtomicBoolean isEncryptAnnotation) {
        WebApplicationContext servletContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());

//        encryptCacheUri.add()
        if (encryptCacheUri.size() > 0) {
            isEncryptAnnotation.set(true);
        }
    }

}
