package demo3.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import demo3.model.Person;
import demo3.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo3/person")
public class PersonController {

    @Autowired
    private PersonRepo personRepo;
    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("/addbatch")
    public ResultBean add1(){


        List<Person> persons = JSONUtil.toList(new JSONArray("[" +
                "{\"firstname\":\"张\",\"lastname\":\"萨\",\"id\":1,\"birthday\":1992-03-05}," +
                "{\"firstname\":\"赵\",\"lastname\":\"大\",\"id\":2,\"birthday\":2001-03-05}," +
                "{\"firstname\":\"钱\",\"lastname\":\"三\",\"id\":3,\"birthday\":2010-03-05}," +
                "{\"firstname\":\"孙\",\"lastname\":\"儒\",\"id\":4,\"birthday\":1980-03-05}," +
                "{\"firstname\":\"李\",\"lastname\":\"啸\",\"id\":5,\"birthday\":1975-03-05}," +
                "{\"firstname\":\"周\",\"lastname\":\"烟\",\"birthday\":2026-03-05}" +
                "]"), Person.class);

        List<Person> people = personRepo.saveAll(persons);
        return ResultBean.ok(people);
    }

    @GetMapping("/add")
    public ResultBean add2(){
        // 如果逐渐重复，是直接覆盖或替换的
//        Person person = JSONUtil.toBean("{\"firstname\":\"刘\",\"lastname\":\"满\",\"id\":1,\"birthday\":1992-03-05}", Person.class);
        // todo 如果主键设置为数值型，有没有指定主键值的话会报错，这里我尝试，自增序列,还没补充完整
        Person person = JSONUtil.toBean("{\"firstname\":\"刘\",\"lastname\":\"满\",\"birthday\":1992-03-05}", Person.class);
        Person people = personRepo.save(person);
        return ResultBean.ok(people);
    }

    @GetMapping("/findAll")
    public ResultBean m2(){
        List<Person> all = personRepo.findAll();
        return ResultBean.ok(all);
    }

    @GetMapping("/findOne")
    public ResultBean m3(Long id){
        Optional<Person> byId = personRepo.findById(id);
        return ResultBean.ok(byId.isPresent()?byId.get():null);
    }

}
