package cn.com.scitc.demo7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

        ArrayList waters=(ArrayList) session.getAttribute("waters");
        if(waters==null){
           waters=new ArrayList();
            session.setAttribute("waters",waters);
        }
        if(waters!=null){
            waters.add(water);
            waters.add(price);
            for(int l = 0; l < waters.size(); l++){
                String ss = (String)waters.get(l);
                //model.addAttribute("ss",ss);
                logger.info("登录名：" + ss );
            }
            model.addAttribute("ss",waters);
           // Enumeration enumeration =session.getAttributeNames();
            // 获取session中所有的键值对

            return "shop";

        }

        return "shop";
    }
}
