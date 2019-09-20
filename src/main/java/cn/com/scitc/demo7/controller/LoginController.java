package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class.getSimpleName());
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login")
    public String page(){
        return "login";
    }

    @RequestMapping(value = "/s")
    public String success(String email,String password){

        Iterable<User> l = userDao.findAll();
        User s = userDao.findByName(email);


        logger.info("登录名："+email+" 密码："+password);
        logger.info("登录名："+s.getName()+" 密码："+s.getPassword());
        //到集合中查找用户是否存在，此处用来模拟数据库验证
        if (email.equals(s.getName())&&password.equals(s.getPassword())){
            return "s";
        }
        return "test";
    }

}
