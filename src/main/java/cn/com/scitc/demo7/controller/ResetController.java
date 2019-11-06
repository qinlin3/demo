package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")

public class ResetController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class.getSimpleName());
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/reset")
    public String reset(){
        return "reset";
    }
    @RequestMapping(value = "/kill")
    public String kill(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        ArrayList name=(ArrayList) session.getAttribute("name");
        String n = (String) name.get(0).toString();

        User a = userDao.findByName(n);
        int b = a.getId();
        userDao.deleteById(b);
        logger.info("id"+b);
        Iterable<User> l = userDao.findAll();

        //model.addAttribute("name",n);

        return "user";
    }
}
