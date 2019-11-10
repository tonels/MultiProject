package com.async.service;

import com.async.model.EmployeeAddresses;
import com.async.model.EmployeeNames;
import com.async.model.EmployeePhone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

	private static Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeNames> getEmployeeName() throws InterruptedException {
		log.info("获取名字 Starts");
		EmployeeNames employeeNameData = restTemplate.getForObject("http://localhost:1210/names", EmployeeNames.class);

		log.info("employeeNameData, {}", employeeNameData);
		Thread.sleep(1000L);	// 延迟 1 秒
		log.info("获取名字 结束");
		return CompletableFuture.completedFuture(employeeNameData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException {
		log.info("获取地址 Starts");
		EmployeeAddresses employeeAddressData = restTemplate.getForObject("http://localhost:1210/addresses", EmployeeAddresses.class);

		log.info("地址数据, {}", employeeAddressData);
		Thread.sleep(1000L);	// 延迟一秒
		log.info("获取地址 结束");
		return CompletableFuture.completedFuture(employeeAddressData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException {
		log.info("获取电话 Starts");
		EmployeePhone employeePhoneData = restTemplate.getForObject("http://localhost:1210/phones", EmployeePhone.class);

		log.info("电话数据, {}", employeePhoneData);
		Thread.sleep(1000L);	// 延迟一秒
		log.info("获取电话 结束");
		return CompletableFuture.completedFuture(employeePhoneData);
	}

}
