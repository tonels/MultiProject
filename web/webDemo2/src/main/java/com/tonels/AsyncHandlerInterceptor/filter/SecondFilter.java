package com.tonels.AsyncHandlerInterceptor.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(2)
@WebFilter(filterName = "SecondFilter",urlPatterns = "/filter/*")
public class SecondFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("SecondFilter 开始....");
        System.out.println("SecondFilter before Response:" + response);
        chain.doFilter(request, response);
        System.out.println("SecondFilter after Response" + response);
        System.out.println("SecondFilter结束.....");
    }

    @Override
    public void destroy() {

    }
}
