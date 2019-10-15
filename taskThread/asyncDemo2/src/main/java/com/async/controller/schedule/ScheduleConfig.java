package com.async.controller.schedule;

import com.async.controller.AsyncController;
import com.async.model.EmployeeAddresses;
import com.async.model.EmployeeNames;
import com.async.model.EmployeePhone;
import com.async.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@EnableScheduling
public class ScheduleConfig {

    private static Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService service;

    @Scheduled(cron = "* */10 * * * ?") // 每隔 time，这种表达式更灵活
    public void s1() throws InterruptedException , ExecutionException {
        log.info("定时 Start");

        CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
        CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName();
        CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone();

        // Wait until they are all done
        CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();

        log.info("EmployeeAddress--> " + employeeAddress.get());
        log.info("EmployeeName--> " + employeeName.get());
        log.info("EmployeePhone--> " + employeePhone.get());
    }
}
