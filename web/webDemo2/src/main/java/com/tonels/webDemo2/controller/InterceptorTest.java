package com.tonels.webDemo2.controller;

import com.tonels.webDemo2.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
// todo 测试interceptor
@RestController
@RequestMapping("/inter")
public class InterceptorTest {

    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        System.out.println(productRepo);

        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}