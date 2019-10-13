//package com.async.schedule;
//
//import com.async.Run;
//import com.async.model.User;
//import com.async.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CompletableFuture;
//
//@EnableScheduling
//@Component
//public class ScheManager {
//
//    private static final Logger logger = LoggerFactory.getLogger(Run.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Scheduled(cron = "*/5 * * * * ?") // 每隔一分钟，这种表达式更灵活
//    public void s2() throws Exception {
//        // Start the clock
//        long start = System.currentTimeMillis();
//        // Kick of multiple, asynchronous lookups
//        CompletableFuture < User > page1 = userService.findUser("PivotalSoftware");
//        CompletableFuture < User > page2 = userService.findUser("CloudFoundry");
//        CompletableFuture < User > page3 = userService.findUser("Spring-Projects");
//        CompletableFuture < User > page4 = userService.findUser("RameshMF");
//        // Wait until they are all done
//        CompletableFuture.allOf(page1, page2, page3, page4).join();
//        // Print results, including elapsed time
//        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//        logger.info("--> " + page1.get());
//        logger.info("--> " + page2.get());
//        logger.info("--> " + page3.get());
//        logger.info("--> " + page4.get());
//
//    }
//
//
//
//
//}
