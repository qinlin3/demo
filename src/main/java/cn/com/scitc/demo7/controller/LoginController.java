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
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class.getSimpleName());
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login")
    public String page() {
        return "login";
    }

    @RequestMapping(value = "/s")
    public String success(String email, String password, HttpServletRequest request, Model model) {


        HttpSession session = request.getSession();
        ArrayList name=(ArrayList) session.getAttribute("name");


        Iterable<User> l = userDao.findAll();
        User s = userDao.findByName(email);

        String credit = s.getCredit().toString();
        model.addAttribute("credit",credit);

        logger.info("登录名：" + email + " 密码：" + password);
        //到集合中查找用户是否存在，此处用来模拟数据库验证
        if (email.equals(s.getName()) && password.equals(s.getPassword())) {

            if(name == null){
                //判断是否有有arrylist集合，没有就创建，并且设置session
                name = new ArrayList();
                session.setAttribute("name",name);
                name.add(s.getName());
            }else{
                //有就把name加入集合
                name.add(s.getName());
            }
            logger.info("name :"+name);
            return "s";

        } else {
            return "test";
        }
    }

    @RequestMapping(value = "/logout")
    public String page1( HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.invalidate();

        /*ArrayList name=(ArrayList) session.getAttribute("name");
        String n = name.get(0).toString();
        logger.info("验证名字："+n);*/
        //logger.info(session.getAttributeNames(name););
        return "login";
    }
}