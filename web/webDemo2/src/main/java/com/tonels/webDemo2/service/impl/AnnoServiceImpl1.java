package com.tonels.webDemo2.service.impl;

import com.tonels.webDemo2.service.AnnotationService;
import org.springframework.stereotype.Service;

@Service
public class AnnoServiceImpl1 implements AnnotationService {

    @Override
    public String init() {
        return "init AnnoImpl1......";
    }
}
