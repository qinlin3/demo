package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class RegisterController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }
    @PostMapping("/create")
    private String createNew(String email,String password){
        User user = new User();
        user.setName(email);
        user.setPassword(password);
        userDao.save(user);
        return "redirect:/user";
    }

}
