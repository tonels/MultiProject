package com.tonels.AsyncHandlerInterceptor.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyAsyncHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {

        System.out.println("interceptor#preHandle called. Thread: " + Thread
                .currentThread().getName());
        return true;

    }

    @Override
    public void postHandle (HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler,
                            ModelAndView modelAndView) throws Exception {

        System.out.println("interceptor#postHandle called. Thread: " +
                Thread.currentThread()
                        .getName());
    }

    @Override
    public void afterCompletion (HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler, Exception ex) throws Exception {

        System.out.println("interceptor#afterCompletion called Thread.: " +
                Thread.currentThread()
                        .getName());
    }

    @Override
    public void afterConcurrentHandlingStarted (HttpServletRequest request,
                                                HttpServletResponse response,
                                                Object handler) throws Exception {

        System.out.println("interceptor#afterConcurrentHandlingStarted. " +
                "Thread: " +
                Thread.currentThread()
                        .getName());
    }
}
