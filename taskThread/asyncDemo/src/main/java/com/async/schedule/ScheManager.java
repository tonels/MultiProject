package com.async.schedule;

import com.async.model.User;
import com.async.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@EnableScheduling
@Component
public class ScheManager {


    @Autowired
    private UserService userService;

    @Scheduled(cron = "* */9 * * * ?") // 每隔一分钟，这种表达式更灵活
    public void s2() throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();
        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = userService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = userService.findUser("CloudFoundry");
//        CompletableFuture < User > page3 = userService.findUser("Spring-Projects");
//        CompletableFuture < User > page4 = userService.findUser("RameshMF");
        // Wait until they are all done
        Void join = CompletableFuture.allOf(page1/*, page2, page3, page4*/).join();
        System.out.println(page1.get());
        System.out.println(page2.get());
//        System.out.println(page3.get());
//        System.out.println(page4.get());
    }


}
