package com.tonels.webDemo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
public class FilterTestController {

    @GetMapping("/test1")
    public String test1(){
        System.out.println("Mothod In Controller");

        return "test1";
    }


}
