package com.tonels.AsyncHandlerInterceptor.config;

import com.tonels.AsyncHandlerInterceptor.interceptor.ProductServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ProductServiceInterceptor productServiceInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProductServiceInterceptor()).addPathPatterns("/inter/*");
        super.addInterceptors(registry);


    }
}
