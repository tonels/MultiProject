package mb_test.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import mb_test.MbRun;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class UserControllerTest {

    final String baseurl = "localhost:1210/user";
    final String query = "/query";
    final String insert = "/insert";
    final String changemoney = "/changemoney";
    final String delete = "/delete";

    @Before
    public void setUp() throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(MbRun.class);
    }

    @Test
    public void queryTest(){
        String body = HttpRequest.get(baseurl + query).execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void addTest(){
        String body = HttpRequest.get(baseurl + insert).execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void updateTest(){
        String body = HttpRequest.get(baseurl + changemoney).execute().body(); //响应的Json 数据
        Console.log(body);
    }

    @Test
    public void deleteTest(){
        String body = HttpRequest.get(baseurl + delete).execute().body(); //响应的Json 数据
        Console.log(body);
    }
}