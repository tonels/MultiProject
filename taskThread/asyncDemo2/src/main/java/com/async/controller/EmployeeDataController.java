package com.async.controller;

import com.async.model.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeDataController {

    private static Logger log = LoggerFactory.getLogger(EmployeeDataController.class);

    @GetMapping(value = "/addresses")
    public EmployeeAddresses getAddresses() {
        EmployeeAddresses employeeAddressesList = new EmployeeAddresses();

        EmployeeAddress e1 = new EmployeeAddress().setHouseNo("1111").setStreetNo("111").setZipCode("111111");
        EmployeeAddress e2 = new EmployeeAddress().setHouseNo("1111").setStreetNo("111").setZipCode("111111");

        ArrayList<EmployeeAddress> list = Lists.newArrayList(e1, e2);

        employeeAddressesList.setEmployeeAddressList(list);

        return employeeAddressesList;
    }

    @GetMapping(value = "/phones")
    public EmployeePhone getPhoneNumbers() {
        EmployeePhone employeePhone = new EmployeePhone();

        ArrayList<String> list = Lists.newArrayList("100000", "200000");

        employeePhone.setPhoneNumbers(list);

        return employeePhone;
    }

    @GetMapping(value = "/names")
    public EmployeeNames getEmployeeName() {
        EmployeeNames employeeNamesList = new EmployeeNames();

        EmployeeName e1 = new EmployeeName().setFirstName("zhang").setLastName("san");
        EmployeeName e2 = new EmployeeName().setFirstName("wang").setLastName("yi");

        List<EmployeeName> list = Lists.newArrayList(e1, e2);

        employeeNamesList.setEmployeeNameList(list);

        int a = 5/0;
        return employeeNamesList;
    }

    @GetMapping("/trade")
    public void t1(Trade trade) {

        System.out.println(trade.getAmount());
        System.out.println(trade.getTradeDate());
    }
}
