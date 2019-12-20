package com.tonels.controller;

import com.tonels.common.User;
import com.tonels.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class Test {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String t1(){
//        userService.addUser()

        for (int i = 1; i <= 10; i++) {
            User user = new User("Bob-" + i);
            userService.addUser(user);
            log.info("用户创建成功.." ,user.getName());
        }
        return "success";
    }


}
