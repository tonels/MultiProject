package com.tonels.AsyncHandlerInterceptor.controller;

import com.tonels.AsyncHandlerInterceptor.service.AnnotationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultBean;

import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/anno")
public class AnnoControllelr {

//    @Autowired
//    private AnnotationService annotationService;

    @Resource(name = "annoServiceImpl1")
    private AnnotationService annotationService1;

    @Resource(name = "annoServiceImpl2")
    private AnnotationService annotationService2;

//    @GetMapping("/testAutowired")
//    public ResultBean te1(){
//        return ResultBean.ok(annotationService.init());
//    }

    @GetMapping("/testResource1")
    public ResultBean te2(){
        return ResultBean.ok(annotationService1.init());
    }

    @GetMapping("/testResource2")
    public ResultBean te3(){
        return ResultBean.ok(annotationService2.init());
    }




}
