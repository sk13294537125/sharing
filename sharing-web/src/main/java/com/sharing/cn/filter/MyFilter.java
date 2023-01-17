package com.sharing.cn.filter;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author ext.shikai1
 */
@Component
@Slf4j
public class MyFilter implements Filter {

    public static List<String> FILTER_URLS = Lists.newArrayList("","");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (FILTER_URLS.contains(request.getRequestURI())) {
            return;
        }
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            chain.doFilter(servletRequest, servletResponse);
        }


    }
}
