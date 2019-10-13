package com.annotation.schedule.schedule;

import com.annotation.schedule.Run;
import com.annotation.schedule.controller.TestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * todo 对于定时任务，这个注解是必须的
 * 启动类里也要加上注解
 *
 * @see Run
 */
@Component
@EnableScheduling
public class Schedule1 {

    @Autowired
    private TestController testController;

    @Scheduled(fixedDelay = 60000) // 每隔五秒
    public void s1() {
        String s = testController.t2();
        System.out.println("testController..." + s);
    }

    @Scheduled(cron = "* */3 * * * ?") // 每隔五秒，这种表达式更灵活
    public void s2() {
        System.out.println("cron..." + LocalDateTime.now());
    }

}
