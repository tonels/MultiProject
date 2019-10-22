package com.redisson.controller;

import com.redisson.model.Ur;
import jodd.util.MathUtil;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class TeController {

    @Autowired
    private RedissonClient redissonClient;

    static long i = 20;
    static long sum = 300;

//    ========================== String =======================
    @GetMapping("/set/{key}")
    public String s1(@PathVariable String key) {
        // 设置字符串
        RBucket<String> keyObj = redissonClient.getBucket(key);
        keyObj.set(key + "1-v1");
        return key;
    }

    @GetMapping("/get/{key}")
    public String g1(@PathVariable String key) {
        // 设置字符串
        RBucket<String> keyObj = redissonClient.getBucket(key);
        String s = keyObj.get();
        return s;
    }

    //    ========================== hash =======================-=

    @GetMapping("/hset/{key}")
    public String h1(@PathVariable String key) {

        Ur ur = new Ur();
        ur.setId(MathUtil.randomLong(1,20));
        ur.setName(key);
      // 存放 Hash
        RMap<String, Ur> ss = redissonClient.getMap("UR");
        ss.put(ur.getId().toString(), ur);
        return ur.toString();
    }

    @GetMapping("/hget/{id}")
    public String h2(@PathVariable String id) {
        // hash 查询
        RMap<String, Ur> ss = redissonClient.getMap("UR");
        Ur ur = ss.get(id);
        return ur.toString();
    }

    // 查询所有的 keys
    @GetMapping("/all")
    public String all(){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> keys1 = keys.getKeys();
        keys1.forEach(System.out::println);
        return keys.toString();
    }

    // ================== ==============读写锁测试 =============================

    @GetMapping("/rw/set/{key}")
    public void rw_set(){
//        RedissonLock.
        RBucket<String> ls_count = redissonClient.getBucket("LS_COUNT");
        ls_count.set("300",360000000l, TimeUnit.SECONDS);
    }

    // 减法运算
    @GetMapping("/jf")
    public void jf(){

        String key = "S_COUNT";

//        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
//        atomicLong.set(sum);
//        long l = atomicLong.decrementAndGet();
//        System.out.println(l);

        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        if (!atomicLong.isExists()) {
            atomicLong.set(300l);
        }

        while (i == 0) {
            if (atomicLong.get() > 0) {
                long l = atomicLong.getAndDecrement();
                        try {
                            Thread.sleep(1000l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                i --;
                System.out.println(Thread.currentThread().getName() + "->" + i + "->" + l);
            }
        }


    }

    @GetMapping("/rw/get")
    public String rw_get(){

        String key = "S_COUNT";
        Runnable r = new Runnable() {
            @Override
            public void run() {
                RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
                if (!atomicLong.isExists()) {
                    atomicLong.set(300l);
                }
                if (atomicLong.get() > 0) {
                    long l = atomicLong.getAndDecrement();
                    i --;
                    System.out.println(Thread.currentThread().getName() + "->" + i + "->" + l);
                }
            }
        };

        while (i != 0) {
            new Thread(r).start();
//            new Thread(r).run();
//            new Thread(r).run();
//            new Thread(r).run();
//            new Thread(r).run();
        }


        RBucket<String> bucket = redissonClient.getBucket(key);
        String s = bucket.get();
        System.out.println("================线程已结束================================" + s);

        return s;
    }

}
