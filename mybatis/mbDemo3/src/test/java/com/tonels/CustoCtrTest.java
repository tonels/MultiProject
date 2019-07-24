package com.tonels;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import tonels.mbdemo3.MbDemo3;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.params.CustoParams;
import utils.ResultBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MbDemo3.class)
public class CustoCtrTest {


    final String baseurl = "localhost:1210/customers";
    final String findAll = "/findAll";
    final String findByCity = "/findByCity";
    final String findById = "/findById";
    final String add = "/add";
    final String update = "/update";
    final String delete = "/delete";
    final String findWhere1 = "/findWhere1";

    private List<CustoParams> lists;

    @Before
    public void setUp() throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(MbDemo3.class);

        CustoParams s1 = new CustoParams().setCustomernumber(12).setCustomername("ss");
        CustoParams s2 = new CustoParams().setCustomernumber(12).setCustomername("ss").setAmount(new BigDecimal(54.211));
        CustoParams s3 = new CustoParams().setCustomernumber(null).setCustomername("ss");
        CustoParams s4 = new CustoParams().setCustomernumber(1).setCustomername("");
        lists = Lists.newArrayList(s1, s2, s3, s4);


    }

    @Test
    public void queryallTest(){
        String body = HttpRequest.get(baseurl + findAll).execute().body(); //响应的Json 数据

        Console.log(body);
    }

    @Test
    public void queryoneTest(){
        String body = HttpRequest.get(baseurl + findById+ "?id=105").execute().body(); //响应的Json 数据
        ResultBean resultBean = JSONUtil.toBean(body, ResultBean.class);
        Console.log(resultBean.getResult());
    }

//    @Test
//    public void querywhere1(){
//        String body = HttpRequest.get(baseurl + findWhere1 +"?").body(JSONUtil.parse(lists.get(0))).execute().body(); //响应的Json 数据
//
//        Console.log(body);
//    }




    @Test
    public void addTest(){
        Customers cu = new Customers().setCity("sh").setCountry("china").setState("cj");
        JSON json = JSONUtil.parse(cu);

        String body = HttpRequest.post(baseurl + add).body(json).execute().body(); //响应的Json 数据
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
