package com.tonels.webDemo2.controller;

import com.tonels.webDemo2.model.User;
import org.springframework.web.bind.annotation.*;

// todo 测试filter
@RestController
@RequestMapping("/filter")
public class FilterTestController {

    @GetMapping("/test1")
    public String test1(String  name){
        System.out.println(name);
        System.out.println(name + "....." + "Mothod In Controller");
        return "test1";
    }

    @PostMapping("/body")
    public String body(@RequestBody User user){
        return user.toString();
    }

    @PostMapping("/map")
    public String map(User user){
        return user.toString();
    }

}
