package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.ProorderDao;
import cn.com.scitc.demo7.model.Proorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class ShopController {
    @Autowired
    ProorderDao proorderDao;
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
    @RequestMapping(value = "/pay")
    public String pay(HttpServletRequest request,String ss){
        HttpSession session = request.getSession();

        ArrayList prices=(ArrayList) session.getAttribute("prices");
        ArrayList name=(ArrayList) session.getAttribute("name");
        ArrayList waters=(ArrayList) session.getAttribute("waters");


        for(int l = 0; l < prices.size(); l++){
            String s = (String) prices.get(l).toString();
            String n = (String) name.get(l).toString();
            String w = (String) waters.get(l).toString();

            Proorder proorder = new Proorder();

            proorder.setPrice(s);
            proorder.setName(n);
            proorder.setProduct(w);

            proorderDao.save(proorder);

            //int s1 = Integer.parseInt(s);
            //model.addAttribute("ss",ss);
            logger.info("价格：" + s+"用户名：" + n+"产品：" + w);
        }
        return "e";
    }
}
