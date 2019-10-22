package com.redisson.controller;

import com.redisson.service.RedissLockUtil;
import org.junit.Test;
import org.redisson.api.RLock;

public class TestLock {

    @Test
    public void t1(){

        RLock oi = RedissLockUtil.lock("oi");


    }



}
