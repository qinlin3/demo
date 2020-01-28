/*package cn.com.scitc.demo7.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;

public class StaticHtmlService<ConfigProperties> {
    protected Logger logger = LoggerFactory.getLogger(StaticHtmlService.class);

    @Autowired
    private TemplateEngine templateEngine;//这是thymeleaf模板处理引擎

    @Autowired
    private ApplicationContext appContext;//这是Spring容器上下文

    @Autowired
    private ConfigProperties config;//这是工程中的配置属性，如静态文件的根目录/usr/local/nginx/html

    /**
     * 生成html静态页面
     * @param modelAndView
     * @param request
     * @param response
     *//*
    public void genHtmlPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        FileWriter fileWriter = null;
        try {
            String fileName = request.getRequestURI();
            if(!fileName.endsWith(".html")) {//将.html结尾的请求生成静态页面
                return;
            }
            fileName = config.getHtmlPath() + fileName;//构造静态html文件完整路径
            File file = new File(fileName);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileWriter = new FileWriter(file);
            SpringWebContext context = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(), modelAndView.getModelMap(), appContext);
            templateEngine.process(modelAndView.getViewName(), context, fileWriter);//将thymeleaf模板生成的结果存入静态文件中
            logger.info("已生成静态文件：" + fileName);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(fileWriter);
        }
    }

    /**
     * 用于更新时删除之前生成的静态页面
     * @param fileName
     *//*
    public void deleteHtmlPage(String fileName) {
        try {
            fileName = config.getHtmlPath() + fileName;
            File file = new File(fileName);
            //删除文件或目录
            FileUtils.deleteQuietly(file);
            logger.info("已删除静态文件：" + fileName);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
*/
