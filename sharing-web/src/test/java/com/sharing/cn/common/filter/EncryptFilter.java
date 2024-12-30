package com.sharing.cn.common.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EncryptFilter implements Filter {

    private final static Set<String> encryptCacheUri = new HashSet<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
