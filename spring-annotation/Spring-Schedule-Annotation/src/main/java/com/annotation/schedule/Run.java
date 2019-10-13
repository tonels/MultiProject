package com.annotation.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class);
    }
}
