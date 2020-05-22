package com.redisson.controller;

import org.apache.catalina.startup.RealmRuleSet;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * todo 异常待解决
 */
@RestController
@RequestMapping("/collection")
public class CollectionTest {

    @Autowired
    private RedissonClient redissonClient;

    // 查询所有的 keys
    @GetMapping("/stream")
    public String stream(){
        RStream<String, String> stream = redissonClient.getStream("test");
        stream.createGroup("testGroup");

        StreamId id1 = stream.add("1", "1");
        StreamId id2 = stream.add("2", "2");

        // contains 2 elements
        Map<StreamId, Map<String, String>> map1 = stream.readGroup("testGroup", "consumer1");

        // ack messages
        stream.ack("testGroup", id1, id2);

        StreamId id3 = stream.add("3", "3");
        StreamId id4 = stream.add("4", "4");

        // contains next 2 elements
        Map<StreamId, Map<String, String>> map2 = stream.readGroup("testGroup", "consumer2");

        PendingResult testGroup = stream.listPending("testGroup");
        return testGroup.toString();


    }





}
