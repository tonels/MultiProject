package demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index.html");
    }

    @GetMapping("/index2")
    public String index2(){
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home(){
        System.out.println("进入方法");

        return new ModelAndView("home.html");
    }




}
