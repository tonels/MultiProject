package com.tonels.AsyncHandlerInterceptor;

import com.tonels.AsyncHandlerInterceptor.config.MyAsyncHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebDemo2 {

    public static void main (String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @SpringBootApplication
    public static class BootApplication extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors (InterceptorRegistry registry) {
            registry.addInterceptor(new MyAsyncHandlerInterceptor());
        }
    }
}
