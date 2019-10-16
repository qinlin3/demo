package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
        user.setCredit(100);
        userDao.save(user);
        return "redirect:/userlist";
    }

    @RequestMapping(value = "/userlist")
    public String userfrom(HttpServletRequest request, Model model){


        Iterable<User> l = userDao.findAll();
        model.addAttribute("l",l);

        //logger.info("价格：" + ip );
        return "user";

    }
}
