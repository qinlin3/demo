package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.StudentDao;
import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.Student;
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
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class.getSimpleName());
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
    public String userfrom(HttpServletRequest request,Model model){
        //String ip = request.getRemoteAddr();

            /*String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("http_client_ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            // 如果是多级代理，那么取第一个ip为客户ip
            if (ip != null && ip.indexOf(",") != -1) {
                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
            }*/
        HttpSession session = request.getSession();
        ArrayList name=(ArrayList) session.getAttribute("name");


        Iterable<User> l = userDao.findAll();
        String n = (String) name.get(0).toString();
        User s = userDao.findByName(n);
        model.addAttribute("name",n);

        logger.info("名字"+n);

        String credit = s.getCredit().toString();
        model.addAttribute("credit",credit);



        /*Iterable<User> l = userDao.findAll();
        User s = userDao.findByName(n);
        */

        logger.info("价格：" + credit );
        return "userfrom";

    }
    @RequestMapping(value = "/new")
    public String create(){
        return "test1";
    }

    @RequestMapping("/name")
    public void getByName(String email) {
        //User s = userDao.findByName(email);
        //logger.info("登录名："+s.getName());

    }
}
