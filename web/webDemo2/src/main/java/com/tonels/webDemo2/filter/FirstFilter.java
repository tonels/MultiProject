package com.tonels.webDemo2.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "FirstFilter",urlPatterns = "/filter/*")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("firstFilter 开始.....");
        chain.doFilter(request,response);
        System.out.println("firstFilter 结束.....");

    }

    @Override
    public void destroy() {

    }
}
