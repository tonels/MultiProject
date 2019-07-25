package tonels.mbdemo3.controller;

import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;
import tonels.mbdemo3.entity.Aa;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.params.CustoParams;
import tonels.mbdemo3.service.CustomersService;
import tonels.mbdemo3.vo.CustoVo;
import tonels.mbdemo3.vo.CustoVo2;
import utils.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomersController{

    @Resource
    private CustomersService customersService;

//  @see localhost:1210//customers/findAll

    @GetMapping("/findAll")
    public ResultBean all(){
        List<Customers> all = customersService.findAll();
        return ResultBean.ok(all) ;
    }

 //   localhost:1210//customers/findAll2
    @GetMapping("/findAll2")
    public ResultBean all2(){
        List<Customers> all = customersService.findAll2();
        List<CustoVo> collect = all.stream().map(e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), CustoVo.class)).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }

    //   localhost:1210//customers/findWhere1?customername=null&customernumber=11&amount=12,对于null和“null”的处理

    //   localhost:1210//customers/findWhere1?customername=null&customernumber=11
    // SELECT cu.customerNumber, cu.customerName, pa.paymentDate, pa.amount FROM customers cu LEFT JOIN payments pa ON cu.customerNumber = pa.customerNumber WHERE cu.customerNumber = ? AND cu.customerName like "%"?"%"


    //   localhost:1210//customers/findWhere1?customername=&customernumber=11&amount=12
    // SELECT cu.customerNumber, cu.customerName, pa.paymentDate, pa.amount FROM customers cu LEFT JOIN payments pa ON cu.customerNumber = pa.customerNumber WHERE cu.customerNumber = ? AND pa.amount > ?
    @GetMapping("/findWhere1")
    public ResultBean where(CustoParams params){
        List<Customers> all = customersService.findWhere1(params);
        List<CustoVo2> collect = all.stream().map(e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), CustoVo2.class)).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }

    //   localhost:1210//customers/findByCity?city=nyc
  //  select customerNumber, customerName, contactLastName, contactFirstName,city from customers cu where cu.city = ?
    @GetMapping("/findByCity")
    public ResultBean cityList(String city){
        List<Customers> all = customersService.findByCity(city);
        return ResultBean.ok(all);
    }

    //   localhost:1210//customers/findByCityAndFirstName?city=nyc&firstname=ss
    //  select customerNumber, customerName, contactLastName, contactFirstName,city from customers cu where cu.city = ? and cu.contactFirstName = ?
    @GetMapping("/findByCityAndFirstName")
    public ResultBean findByCityAndFirstName(/*@RequestParam(required = true)*/ String city,@RequestParam(name = "firstname") String contactfirstname){
        List<Customers> all = customersService.findByCityAndFirstName(city,contactfirstname);
        return ResultBean.ok(all);

    }

    //   localhost:1210//customers/findById?id=11k
    //  select customerNumber, customerName, contactLastName, contactFirstName,city from customers cu where cu.customerNumber = ?
    @GetMapping("/findById")
    public ResultBean byid(@RequestParam(defaultValue = "1",required = false,/*value = "ss",*/name = "ls") Integer id){
        List<Customers> all = customersService.findById(id);
        return ResultBean.ok(all);
    }

//    localhost:1210//customers/add
// insert into customers ( customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )

    @PostMapping("/add")
    public ResultBean save(@RequestBody Customers customers){
        return ResultBean.ok(customersService.save(customers));
    }

// update customers set customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? where customers.customerNumber = ?
    @PutMapping("/update")
    public ResultBean update(@RequestBody Customers customers){
        customersService.update(customers);
        return ResultBean.ok();
    }

// delete from customers where customerNumber in ( ? , ? )
//
    @DeleteMapping("/delete")
    public ResultBean delete(@RequestBody Set<Integer> ids){
        customersService.delete(ids);
        return ResultBean.ok();
    }

    @PostMapping("/adaa")
    public ResultBean aa(@RequestBody @Valid Aa aa){
        System.out.println(aa);
        return ResultBean.ok("dd");
    }









}
