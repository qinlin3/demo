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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
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

        String address = s.getAddress();
        model.addAttribute("address",address);



        /*Iterable<User> l = userDao.findAll();
        User s = userDao.findByName(n);
        */

        logger.info("价格：" + credit );
        if (s.getVal().equals("0")){
            return "userfrom";
        }
        return "u";

    }

    @RequestMapping(value = "/edit")
    public String edit(){
        return "address";
    }

    @RequestMapping(value = "/editAddress")
    public String editAddress(String address,HttpServletRequest request){
        HttpSession session = request.getSession();
        ArrayList name=(ArrayList) session.getAttribute("name");
        String n = (String) name.get(0).toString();
        User s = userDao.findByName(n);
        s.setAddress(address);
        userDao.save(s);
        logger.info("名字:"+ n);
        logger.info("地址"+ address);
        return "n";
    }

    @RequestMapping(value = "/new")
    public String create(){
        return "test1";
    }

    @RequestMapping(value = "/video")
    public String video(){
        return "video";
    }

    @RequestMapping(value = "/videotest")
    public String videotest(){
        return "video1";
    }

    /*@ResponseBody
    @RequestMapping(value = "/getVideoSrc")
    public OutputStream getVideoSrc(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse){
        //1.创建文件对象
        File f = new File("E:/test/1.mp4");
        //2.获取文件名称
        String fileName = f.getName();
        //3.导出文件
        String agent = httpServletRequest.getHeader("User-Agent").toUpperCase();
        InputStream fis = null;
        OutputStream os = null;
        try {
            //4.获取输入流
            fis = new BufferedInputStream(new FileInputStream(f.getPath()));
            byte[] buffer;
            buffer = new byte[fis.available()];
            fis.read(buffer);
            httpServletResponse.reset();
            //5.由于火狐和其他浏览器显示名称的方式不相同，需要进行不同的编码处理
            if(agent.indexOf("FIREFOX") != -1){//火狐浏览器
                httpServletResponse.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO-8859-1"));
            }else{//其他浏览器
                httpServletResponse.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
            }
            //6.设置response编码
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.addHeader("Content-Length", "" + f.length());
            //设置输出文件类型
            httpServletResponse.setContentType("video/mpeg4");
            //7.获取response输出流
            os = httpServletResponse.getOutputStream();
            os.flush();
            //8.输出文件
            os.write(buffer);
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            //关闭流
            try {
                if(fis != null){ fis.close(); }

                if(os != null){ os.flush(); }

                if(os != null){os.close(); }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return os;
    }*/

    @RequestMapping(value = "/shoplist")
    public String shoplist(HttpServletRequest request,String ss,Model model){

            HttpSession session = request.getSession();
            ArrayList name=(ArrayList) session.getAttribute("name");
            //String n = name.get(0).toString();
            //logger.info("总价"+ name);
            String n = (String) name.get(0).toString();
            User u = userDao.findByName(n);
            String credit = u.getCredit().toString();
            model.addAttribute("credit",credit);
            if(name != null){
                if(u.getVal().equals("0")){
                    return "s";
                }
                return "a";
            }
            else {
                return "login";
            }
    }

    @RequestMapping("/name")
    public void getByName(String email) {
        //User s = userDao.findByName(email);
        //logger.info("登录名："+s.getName());

    }
}
