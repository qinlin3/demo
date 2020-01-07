package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.ImagesaddressDao;
import cn.com.scitc.demo7.model.Imagesaddress;
import cn.com.scitc.demo7.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 网页生成
 * @author
 *
 */
@Controller
@RequestMapping(value = "/")

public class MakeHtmlController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class.getSimpleName());
    @Autowired
    private ImagesaddressDao imagesaddressDao;

    @RequestMapping(value = "/makehtml")
    public String makehtml(String file_name) {
        //Iterable<Imagesaddress> list = imagesaddressDao.findAll();
        Imagesaddress i = imagesaddressDao.findByImages(file_name);
        String a = i.getId().toString();
        logger.info(a);
        return "studentfrom";
    }

}
