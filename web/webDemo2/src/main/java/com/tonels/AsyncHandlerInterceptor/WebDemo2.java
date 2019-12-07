package com.tonels.AsyncHandlerInterceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebDemo2 {
    public static void main(String[] args) {
        SpringApplication.run(WebDemo2.class);
    }

}
