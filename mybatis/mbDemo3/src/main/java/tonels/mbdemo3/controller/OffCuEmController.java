package tonels.mbdemo3.controller;

import org.springframework.web.bind.annotation.*;
import tonels.mbdemo3.convertor.UserConverter;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.entity.Offices;
import tonels.mbdemo3.service.CustomersService;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.List;

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
