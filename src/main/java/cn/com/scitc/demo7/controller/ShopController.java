package cn.com.scitc.demo7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class ShopController {
    private Logger logger = LoggerFactory.getLogger(ShopController.class.getSimpleName());
    @RequestMapping(value = "/shop")
    public String shop(HttpServletRequest request, Model model){
        //获取到Session对象
        HttpSession session = request.getSession();

        String water=request.getParameter("water");
        String price=request.getParameter("price");
        String all = water + price;

        ArrayList waters=(ArrayList) session.getAttribute("waters");
        //Map<String, String> waters =(Map<String,String>) session.getAttribute("waters");
        //map.put("id", rb.getReaderID());
        //map.put("name", rb.getReaderName());
        //session.setAttribute("map",map);
        if(waters==null){
           waters=new ArrayList();
            session.setAttribute("waters",waters);
        }
        if(waters!=null){
            waters.add(all);
            for(int l = 0; l < waters.size(); l++){
                String ss = (String)waters.get(l);
                //model.addAttribute("ss",ss);
                //logger.info("登录名：" + ss );
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
        waters.remove(ss);
        for(int l = 0; l < waters.size(); l++){
            String s = (String)waters.get(l);
            //model.addAttribute("ss",ss);
            logger.info("登录名：" + s );
        }
        return "shop";
    }
}
