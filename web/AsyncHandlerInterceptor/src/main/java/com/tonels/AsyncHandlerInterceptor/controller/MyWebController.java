package com.tonels.AsyncHandlerInterceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class MyWebController {

    @GetMapping("/")
    public Callable<String> handleTestRequest () {

        System.out.println("controller#handler called. Thread: " +
                Thread.currentThread().getName());

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call () throws Exception {
                System.out.println("controller-callable#async task started. Thread: " +
                        Thread.currentThread().getName());
                Thread.sleep(300);
                System.out.println("controller-callable#async task finished");
                return "async result";
            }
        };

        System.out.println("controller#handler finished");
        return callable;
    }
}