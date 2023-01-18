package com.sharing.cn.configuration;

import com.sharing.cn.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author ext.shikai1
 */
public class FilterConfig {

    //@Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("Filter1");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
