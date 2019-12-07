package com.tonels.AsyncHandlerInterceptor.service.impl;

import com.tonels.AsyncHandlerInterceptor.service.AnnotationService;
import org.springframework.stereotype.Service;

@Service
public class AnnoServiceImpl2 implements AnnotationService {

    @Override
    public String init() {
        return "Init AnnoImpl2.......";
    }
}
