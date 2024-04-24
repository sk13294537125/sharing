package com.sharing.cn.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ext.shikai1
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String property = "D:/local-workspace/RuoYi/ruoyi-admin/src/main/resources/static/img";
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(property);
        registry.addResourceHandler("/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }


}
