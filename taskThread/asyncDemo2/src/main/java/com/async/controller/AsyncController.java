package com.async.controller;

import com.async.model.EmployeeAddresses;
import com.async.model.EmployeeNames;
import com.async.model.EmployeePhone;
import com.async.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class AsyncController {

	private static Logger log = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private AsyncService service;

	@GetMapping("/testAsynch")
	public void testAsynch() throws InterruptedException, ExecutionException {
		log.info("测试开始");

		CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
		CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName();
		CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone();

		CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();
		
		log.info("雇员地址--> " + employeeAddress.get());
		log.info("雇员名字--> " + employeeName.get());
		log.info("雇员电话--> " + employeePhone.get());
	}
}
