package querydsl.tonels.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import querydsl.tonels.security.SecurityUtils;

@RestController
public class UserController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/cUser")
    public String user(){
        return SecurityUtils.getCurrentUserLogin();
    }

}
