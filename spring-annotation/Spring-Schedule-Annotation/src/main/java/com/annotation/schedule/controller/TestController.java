package com.annotation.schedule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/index")
public class TestController {

    @GetMapping
    public String t1(){
        return "index home";
    }

    // schedule 调度用
    public String t2(){
        return "schedule time：" + LocalDateTime.now();
    }
}
