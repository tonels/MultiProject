package com.tonels.webDemo2.controller;

import com.tonels.webDemo2.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/http")
public class HttpController {

    @GetMapping("/get1")
    public String  get1(){
        return "get 1 ....";
    }

    @GetMapping("/get2")
    public String  get2(String name){
        return "get 2 ...." + name ;
    }

    @PostMapping("/post1")
    public User post1(){
        return new User(1,"tl");
    }

    @PostMapping("/post2")
    public User post2(@RequestBody User user){
        return user;
    }

    @PutMapping("/put1")
    public User put1(){
        return new User(1,"put");
    }

    @PutMapping("/put2")
    public User put1(@RequestBody User user){
        return user;
    }

    @DeleteMapping("/delete1")
    public String del1(Set<Integer> ids){
        return ids.toString();
    }



}
