package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

@Controller
@RequestMapping(value = "/")
public class RegisterController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }

/*    @RequestMapping("/getcode")
    @ResponseBody
    public int getcode(String phone) {
        long l=System.currentTimeMillis();
        int k1=(int) (l%10000);
        String code=String.valueOf(k1);
        //session中保存我后台生成的code,为了将来拿出来与用户提交的进行比较。
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("autocode",code);
        //成功返回0，失败返回1
        if(phone!=null){
            //把后台生成的code和所发送的手机号传进发送消息类，调用执行。
            SmsService.send(phone,code);
            return 0;
        }else{
            return 1;
        }
    }*/

    @PostMapping("/create")
    private String createNew(String email,String password){
        User s = userDao.findByName(email);
        if(s == null){
            User user = new User();
            user.setName(email);
            user.setPassword(password);
            user.setCredit(100);
            userDao.save(user);
            return "redirect:/userlist";
        }else {
            return "register";
        }


    }

    @RequestMapping(value = "/userlist")
    public String userfrom(HttpServletRequest request, Model model){


        Iterable<User> l = userDao.findAll();
        model.addAttribute("l",l);

        //logger.info("价格：" + ip );
        return "user";

    }
}
