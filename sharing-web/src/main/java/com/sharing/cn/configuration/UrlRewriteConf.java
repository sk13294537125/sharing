package com.sharing.cn.configuration;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 不显示url
 */
@Configuration
public class UrlRewriteConf {

    @Bean
    public FilterRegistrationBean urlRewrite(){
        UrlRewriteFilter rewriteFilter=new UrlRewriteFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean(rewriteFilter);
        registration.setUrlPatterns(Arrays.asList("/*"));
        Map initParam=new HashMap();
        initParam.put("confPath","urlrewirte.xml");
        initParam.put("infoLevel","INFO");
        registration.setInitParameters(initParam);
        return  registration;
    }

}
