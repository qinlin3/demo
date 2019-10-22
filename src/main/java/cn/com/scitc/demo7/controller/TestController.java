package cn.com.scitc.demo7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class TestController {
    @RequestMapping(value="/test3")
    public String testJsp(){
        System.out.println("test controller===========");
        return ("demo");
    }
}