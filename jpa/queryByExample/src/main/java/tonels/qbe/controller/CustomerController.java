package tonels.qbe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jpaCommon.model.CustomersEntity;
import tonels.qbe.service.CustomerService;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cust")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @GetMapping("/findAllDyna") // 单 表
    public ResultBean all1(CustomersEntity customers){
        List<CustomersEntity> all = customerService.findAllByExample(customers);
        return ResultBean.ok(all);

    }


}
