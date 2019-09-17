package cn.com.scitc.demo7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")

public class ResetController {
    @RequestMapping(value = "/reset")
    public String reset(){
        return "reset";
    }
}
