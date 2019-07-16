package tonels.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tonel.model.CustomersEntity;
import tonels.service.CustomerService;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @GetMapping("/findAllDynamic1") // 单 表
    public ResultBean all1(CustomersEntity customers){
        List<CustomersEntity> all = customerService.findAllBySpecification(customers);
        return ResultBean.ok(all);
    }

    @GetMapping("/findAllDynamic2") // 单 表
    public ResultBean all2(){
        CustomersEntity c1 = new CustomersEntity().setCity("NYC");
        List<CustomersEntity> all = customerService.findBySpecification2(c1);
        return ResultBean.ok(all);
    }


}
