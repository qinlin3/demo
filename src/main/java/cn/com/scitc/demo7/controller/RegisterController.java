package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.User;
import cn.com.scitc.demo7.utils.MD5Util;
import cn.com.scitc.demo7.utils.RandomUtil;
import cn.com.scitc.demo7.utils.SendSms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "/")
public class RegisterController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class.getSimpleName());
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
/*private static final String KEY = "abc123"; // KEY为自定义秘钥

    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST, headers = "Accept=application/json")
    public Map<String, Object> sendMsg(@RequestBody Map<String,Object> requestMap) {
        String phoneNumber = requestMap.get("phoneNumber").toString();
        String randomNum = RandomUtil.getRandom(6);// 生成随机数
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        //获取当前时间
        Calendar c = Calendar.getInstance();
        //对时间加五分钟操作
        c.add(Calendar.MINUTE, 5);
        String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
        SendSms.send("phoneNumber","randomNum"); //此处执行发送短信验证码方法
        //logger.info("名字"+obj);
        /*String hash =  MD5Util.getMD5Code(KEY + "@" + currentTime + "@" + randomNum);//生成MD5值
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("hash", hash);
        resultMap.put("tamp", currentTime);
        return resultMap; //将hash值和tamp时间返回给前端
    }*/
@RequestMapping("/sendMsg")
@ResponseBody
public String sendMsg(String phoneNumber) {

    String randomNum = RandomUtil.getRandom(6);
    logger.info("名字"+phoneNumber);
    //session中保存我后台生成的code,为了将来拿出来与用户提交的进行比较。
    SendSms.send(phoneNumber,randomNum);
    return null;
}


    /*@RequestMapping(value = "/validateNum", method = RequestMethod.POST, headers = "Accept=application/json")
    public Map<String, Object> validateNum(@RequestBody Map<String,Object> requestMap) {
        String requestHash = requestMap.get("hash").toString();
        String tamp = requestMap.get("tamp").toString();
        String msgNum = requestMap.get("msgNum").toString();
        String hash = MD5Utils.getMD5Code(KEY + "@" + tamp + "@" + msgNum);
        if (tamp.compareTo(currentTime) > 0) {
            if (hash.equalsIgnoreCase(requestHash)){
                //校验成功
            }else {
                //验证码不正确，校验失败
            }
        } else {
            // 超时
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
