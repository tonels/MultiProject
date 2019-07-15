package tonels.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tonels.storeProcedure.UserRepository;
import utils.ResultBean;

import javax.annotation.Resource;

@RestController
@RequestMapping("/jpa")
public class StoreController {

    @Resource
    private UserRepository userRepository;



    @GetMapping("/g1")
    public ResultBean g1(Integer num){
        Integer a = userRepository.plus1inout(num);
        return ResultBean.ok(a);
    }

    @GetMapping("/g2")
    public ResultBean g2(Integer num){
        Integer a = userRepository.plus1BackedByOtherNamedStoredProcedure(num);
        return ResultBean.ok(a);
    }


}
