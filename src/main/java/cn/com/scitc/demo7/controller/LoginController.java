package cn.com.scitc.demo7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping(value = "/login")
    public String page(){
        return "login";
    }

}
