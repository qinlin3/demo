package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.ProorderDao;
import cn.com.scitc.demo7.dao.UserDao;
import cn.com.scitc.demo7.model.Proorder;
import cn.com.scitc.demo7.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class ShopController {
    @Autowired
    ProorderDao proorderDao;
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(ShopController.class.getSimpleName());
    @RequestMapping(value = "/shop")
    public String shop(HttpServletRequest request, Model model){
        //获取到Session对象
        HttpSession session = request.getSession();

        String water=request.getParameter("water");
        String price=request.getParameter("price");
        String all = water + price;

        ArrayList waters=(ArrayList) session.getAttribute("waters");
        ArrayList prices=(ArrayList) session.getAttribute("prices");
        //Map<String, String> waters =(Map<String,String>) session.getAttribute("waters");
        //map.put("id", rb.getReaderID());
        //map.put("name", rb.getReaderName());
        //session.setAttribute("map",map);
        if(waters==null && prices == null){
           waters=new ArrayList();
           prices=new ArrayList();
            session.setAttribute("waters",waters);
            session.setAttribute("prices",prices);
        }
        if(waters!=null && prices!=null){
            waters.add(all);
            prices.add(price);
            for(int l = 0; l < waters.size(); l++){
                String ss = (String)waters.get(l);
                //model.addAttribute("ss",ss);
                //logger.info("登录名：" + ss );
            }
            for(int n = 0; n < prices.size(); n++){
                String nn = (String) prices.get(n);
                //int a = Integer.parseInt(nn);
                model.addAttribute("nn",nn);
                //int m = Integer.parseInt(nn);
                logger.info("价格：" + nn );
            }
            model.addAttribute("ss",waters);
           // Enumeration enumeration =session.getAttributeNames();
            // 获取session中所有的键值对

            return "shop";

        }


        return "shop";
    }
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,String ss){
        HttpSession session = request.getSession();
        ArrayList waters=(ArrayList) session.getAttribute("waters");
        ArrayList name =(ArrayList) session.getAttribute("name");
        waters.remove(ss);
        for(int l = 0; l < waters.size(); l++){
            String s = (String)waters.get(l);
            //model.addAttribute("ss",ss);
            logger.info("登录名：" + s );
        }
        logger.info("name ："+name);
        return "shop";
    }
    /*int a = prices.size();
        Date now = new Date();
        Date time = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(now));// new Date()为获取当前系统时间
        time= df.parse(df.format(new Date()));
        System.out.println(time);
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = formatter.format(new Date());
        Date date = formatter.parse(date1);*/
    @RequestMapping(value = "/pay")
    public String pay(HttpServletRequest request,String ss) throws ParseException {
        int all = 0;
        HttpSession session = request.getSession();

        ArrayList prices=(ArrayList) session.getAttribute("prices");
        ArrayList name=(ArrayList) session.getAttribute("name");
        ArrayList waters=(ArrayList) session.getAttribute("waters");


        Date date = new Date();
        Timestamp timeStamep = new Timestamp(date.getTime());

        int[] p = new int[100];
        int a = 0;
        String n = (String) name.get(0).toString();
        User u = userDao.findByName(n);
        String address = u.getAddress();
        logger.info("地址1："+ address);
        for(int l = 0; l < waters.size(); l++){
            String s = (String) prices.get(l).toString();
            String w = (String) waters.get(l).toString();

            Proorder proorder = new Proorder();
            proorder.setPrice(s);
            proorder.setName(n);
            proorder.setProduct(w);
            proorder.setDate(timeStamep);
            proorder.setAddress(address);
            proorderDao.save(proorder);
        }
        for (int i = 0; i < prices.size();i++){
            String s = (String) prices.get(i).toString();
            p[i] = Integer.parseInt(s);
            a = a + p[i];
            logger.info("1总价:"+ a);

        }

        User s = userDao.findByName(n);
        int m = s.getCredit();
        int q = m - a;
        s.setCredit(q);
        userDao.save(s);

        logger.info("名字:"+ s.getName());
        logger.info("剩余金额"+ q);

        session.removeAttribute("waters");
        session.removeAttribute("prices");


        return "h";
    }


    @RequestMapping(value = "/return")
    public String returnn(HttpServletRequest request,String ss,Model model){
        HttpSession session = request.getSession();
        ArrayList name=(ArrayList) session.getAttribute("name");
        //String n = name.get(0).toString();
        //logger.info("总价"+ name);
        String n = (String) name.get(0).toString();
        User u = userDao.findByName(n);
        String credit = u.getCredit().toString();
        model.addAttribute("credit",credit);
        if(name != null){
            if (u.getVal().equals("0")){
                return "s";
            }else {
                return "a";
            }

        }
        else {
            return "login";

        }

    }
}
