package demo1.controller;

import com.google.common.collect.Sets;
import demo1.model.Authority;
import demo1.model.User;
import demo1.repo.AuthRepo;
import demo1.repo.UserRepo;
import org.springframework.web.bind.annotation.*;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    private UserRepo userRepo;
    @Resource
    private AuthRepo authRepo;

    // local
    @GetMapping("/add")
    public ResultBean save(String name,String auth){

        final User user = new User();
        user.setLogin(name).setPassword("InitializingInitializingInitializingInitializingInitializing");
        user.setCreatedBy("liang");

        final Authority authority = new Authority();
        authority.setName(auth);
        Set<Authority> set1 = Sets.newHashSet(authority);

        user.setAuthorities(set1);
        final User user1 = userRepo.save(user);
        return ResultBean.ok(user1);

    }

    @GetMapping("/select")
    public ResultBean select(){

        final List<User> all = userRepo.findAll();
        return  ResultBean.ok(all);
    }


    @GetMapping("/update")
    public ResultBean update(){

        final User user = new User();
        user.setId(2L).setEmail("sd22@ls.com").setLogin("ls22").setPassword("InitializingInitializingInitializingInitializingInitializing");
        user.setCreatedBy("liang2");
        userRepo.save(user);
        return  ResultBean.ok();
    }

    @GetMapping("/delete")

    public ResultBean del(Long id){

        userRepo.deleteById(id);
        return ResultBean.ok();
    }


}
