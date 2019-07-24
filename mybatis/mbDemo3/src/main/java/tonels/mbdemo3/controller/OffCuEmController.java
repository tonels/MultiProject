package tonels.mbdemo3.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tonels.mbdemo3.convertor.UserConverter;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.service.CustomersService;
import utils.ResultBean;

import javax.annotation.Resource;

@RestController
@RequestMapping("/off")
public class OffCuEmController {

    @Resource
    private CustomersService customersService;

    @Resource
    private UserConverter userConverter;

    @PostMapping("/add")
    public ResultBean save(Customers customers){
        return ResultBean.ok(customersService.save(customers));
    }






}
