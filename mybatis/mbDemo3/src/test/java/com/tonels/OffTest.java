package com.tonels;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import tonels.mbdemo3.MbDemo3;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MbDemo3.class)
public class OffTest {


    final String baseurl = "localhost:1210/customers";
    final String findAll = "/findAll";
    final String findByCity = "/findByCity";
    final String findById = "/findById";
    final String add = "/add";
    final String update = "/update";
    final String delete = "/delete";

    @Before
    public void setUp() throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(MbDemo3.class);
    }

    @Test
    public void queryallTest(){
        String body = HttpRequest.get(baseurl + findAll).execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void queryoneTest(){
        String body = HttpRequest.get(baseurl + findById + "?id=104").execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void addTest(){
        String body = HttpRequest.post(baseurl + add).execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void updateTest(){
        String body = HttpRequest.put(baseurl + update).execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void deleteTest(){
        String body = HttpRequest.delete(baseurl + delete).execute().body(); //响应的Json 数据
        Console.log(body);
    }






}
