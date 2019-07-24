package tonels.mbdemo3.controller;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tonels.mbdemo3.convertor.UserConverter;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.entity.Offices;
import tonels.mbdemo3.service.CustomersService;
import tonels.mbdemo3.vo.OfficesVO;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Resource
    private CustomersService customersService;

    @Resource
    private UserConverter userConverter;

    @PostMapping("/add")
    public ResultBean save(Customers customers){
        return ResultBean.ok(customersService.save(customers));
    }

    @PutMapping("/update")
    public ResultBean update(Customers customers){
        return ResultBean.ok(customersService.update(customers));
    }

    @DeleteMapping("/delete")
    public ResultBean delete(Integer id){
        customersService.delete(id);
        return ResultBean.ok();
    }

    @GetMapping("/findAll")
    public ResultBean all(){
        customersService.findAll();
        return ResultBean.ok();
    }

    @GetMapping("/findByCity")
    public ResultBean cityList(String city){
        return ResultBean.ok(customersService.findByCity(city));

    }

    @GetMapping("/findById")
    public ResultBean byid(Integer id){

        List<Offices> list = customersService.findById(id);

        return ResultBean.ok(userConverter.toListDTO(list));

    }






}
