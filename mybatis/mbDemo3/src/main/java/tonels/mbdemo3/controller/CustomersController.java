package tonels.mbdemo3.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tonels.mbdemo3.common.AbstractConvertor;
import tonels.mbdemo3.convertor.UserConverter;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.entity.Offices;
import tonels.mbdemo3.params.CustoParams;
import tonels.mbdemo3.service.CustomersService;
import tonels.mbdemo3.vo.CustoVo;
import tonels.mbdemo3.vo.CustoVo2;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Resource
    private CustomersService customersService;

    @Resource
    private UserConverter userConverter;



    @GetMapping("/findAll")
    public ResultBean all(){
        List<Customers> all = customersService.findAll();
        return ResultBean.ok(all) ;
    }

    @GetMapping("/findAll2")
    public ResultBean all2(){
        List<Customers> all = customersService.findAll2();
        List<CustoVo> collect = all.stream().map(e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), CustoVo.class)).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }

    @GetMapping("/findWhere1")
    public ResultBean where(CustoParams params){
        List<Customers> all = customersService.findWhere1(params);
        List<CustoVo2> collect = all.stream().map(e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), CustoVo2.class)).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }


    @GetMapping("/findByCity")
    public ResultBean cityList(String city){
        List<Customers> all = customersService.findByCity(city);
        List<CustoVo> vos = all.stream().map(e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), CustoVo.class)).collect(Collectors.toList());
        return ResultBean.ok(vos);
    }

    @GetMapping("/findById")
    public ResultBean byid(Integer id){
        List<Offices> list = customersService.findById(id);
        return ResultBean.ok(userConverter.toListDTO(list));
    }



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






}
