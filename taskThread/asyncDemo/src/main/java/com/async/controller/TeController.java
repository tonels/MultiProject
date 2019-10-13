package com.async.controller;

import com.async.Run;
import com.async.model.User;
import com.async.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultBean;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/index")
public class TeController {

    private static final Logger logger = LoggerFactory.getLogger(Run.class);
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResultBean t1() throws Exception {
            // Start the clock
            long start = System.currentTimeMillis();

            CompletableFuture<User> page1 = userService.findUser("tonels");

            // 等待
            CompletableFuture.allOf(page1).join();
            // 打印结果
            logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
            logger.info("--> " + page1.get());
        return ResultBean.ok();
    }

    @GetMapping("/s1")
    public ResultBean t2(){
        return ResultBean.ok("success");
    }



}
