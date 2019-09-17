package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.StudentDao;
import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.Student;
import cn.com.scitc.demo7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/test")
    private String test(){
        return "test";
    }
    @RequestMapping(value = "/student")
    public String studentfrom(Model model) {
        Iterable<Student> list = studentDao.findAll();
        model.addAttribute("list", list);
        return "studentfrom";
    }
    @RequestMapping(value = "/user")
    public String userfrom(Model model){
        Iterable<User> l = userDao.findAll();
        model.addAttribute("l",l);
        return "userfrom";
    }
}
