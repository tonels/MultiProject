package com.tonels.modelTable.controller;

import cn.hutool.core.util.RandomUtil;
import com.tonels.modelTable.model.S1;
import com.tonels.modelTable.repo.StuRepo;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/s")
public class StuController {

    @Resource
    private StuRepo stuRepo;

    @GetMapping("/save")
    public void save() {
        for (int i = 100; i < 120; i++) {
            S1 s1 = new S1();
            S1 s11 = s1.setSex("" + RandomUtil.randomNumber()).setSName("test" + i).setSBirthday(LocalDate.now());
            S1 save = stuRepo.save(s11);
        }
    }

     // 这里的具体的异常为什么捕获不到，exception,是可以捕获的
    @GetMapping("/fid")
    public String testVersion(Integer id,String n1,String n2) throws Exception/*StaleObjectStateException,InterruptedException */{
        try {
            S1 s1 = stuRepo.findById(id).get();
            s1.setSName(n1);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ss");
                    S1 s2 = stuRepo.findById(id).get();
                    s2.setSName(n2);
                    stuRepo.save(s2);
                }
            }).start();
                Thread.sleep(1);

            stuRepo.save(s1);
        }catch (StaleObjectStateException e){
            throw new StaleObjectStateException("e",e);
        }
        return "testVersion";
        }

    @GetMapping("/update")
    public String updateNameById(){
        stuRepo.updateNameById("xuxuan2",23);
        return "updateNameById";
    }

    @GetMapping("/id")
    public S1 f1(Integer id){
        Optional<S1> byId = stuRepo.findById(id);
        return byId.isPresent()? byId.get():null;
    }


}

