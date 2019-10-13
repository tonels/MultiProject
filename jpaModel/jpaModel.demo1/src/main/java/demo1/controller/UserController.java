package demo1.controller;

import com.google.common.collect.Sets;
import demo1.log.OperateModule;
import demo1.log.OperateType;
import demo1.log.SystemLog;
import demo1.model.Authority;
import demo1.model.User;
import demo1.repo.AuthRepo;
import demo1.repo.UserRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import utils.ResultBean;

import javax.annotation.Resource;
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
    @PostMapping("/add")
    public ResultBean save(@RequestBody User user){

        Set<Authority> authorities = user.getAuthorities();
        final Authority authority = new Authority();
        authority.setName("admin");
        authorities = Sets.newHashSet(authority);

        user.setAuthorities(authorities);

        User save = userRepo.save(user);
        return ResultBean.ok(save);
    }

    // local
    @PostMapping("/add2")
    @SystemLog(description = "用户新增", module = OperateModule.USER, opType = OperateType.create,
            primaryKeyBelongClass = User.class, primaryKeyName = "id")
    public ResultBean save2(@RequestBody User user){
        User save = null;
        try {
            save = userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            String message = e.getRootCause().getMessage();
            System.out.println(message);
            throw new DataIntegrityViolationException("");
        }catch (Throwable t){
        }
        return ResultBean.ok(save);
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
