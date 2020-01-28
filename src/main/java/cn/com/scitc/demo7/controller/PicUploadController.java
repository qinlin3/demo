package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.dao.CmsHtmlDao;
import cn.com.scitc.demo7.dao.ImagesaddressDao;
import cn.com.scitc.demo7.model.CmsHtml;
import cn.com.scitc.demo7.model.Imagesaddress;
import cn.com.scitc.demo7.utils.CreateHtmlUtils;
import cn.com.scitc.demo7.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * 图片上传
 * @author 吴洋
 *
 */
@Controller
@RequestMapping(value = "/")
public class PicUploadController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class.getSimpleName());
    @Autowired
    private ImagesaddressDao imagesaddressDao;
    @Autowired
    private CmsHtmlDao cmsHtmlDao;
    @RequestMapping("/picUpload")
    public String picUpload(){
        return "picUpload1";
    }
    @ResponseBody
    @PostMapping("/upload")
    public Object upload(MultipartFile fileUpload) throws IOException {
        //name是根据时间命名的网页名称，pathname是文件在某目录下的具体位置，用于定位具体的文件。
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        String file_Name = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        String filePath = "E:/demo10/src/main/resources/static/images/";
        //将图片保存到static文件夹里
        fileUpload.transferTo(new File(filePath+file_Name));
        //保存图片信息到数据库中
        //获取当前时间并转换为字符串格式
        Date date = new Date();
        Timestamp timeStamep = new Timestamp(date.getTime());
        String times = timeStamep.toString();
        //去除时间中的-和.
        String str1 = times.replaceAll("[[\\s-.:punct:]]","");

        String name = str1;
        //"liuren";

        String pathname = "/images/"+ file_Name;
        Imagesaddress imagesaddress = new Imagesaddress();
        imagesaddress.setImages(fileName);
        imagesaddress.setFileName(file_Name);
        imagesaddress.setAddress(pathname);
        imagesaddressDao.save(imagesaddress);
        logger.info(fileName);
        logger.info(pathname);

        //保存网页与文件关联的信息
        CmsHtml cmsHtml = new CmsHtml();
        cmsHtml.setFilename(fileName);
        cmsHtml.setFileName(file_Name);
        cmsHtml.setHtmlName(name);
        cmsHtml.setPathname(pathname);
        cmsHtmlDao.save(cmsHtml);
        //创建页面
        // CreateHtmlUtils.CreateHtml(pathname,name);

       /* Imagesaddress i =  imagesaddressDao.findByImages(file_Name);
        //Imagesaddress i = (Imagesaddress) imagesaddressDao.findByImages(fileName);
        if(i != null){
            //logger.info(i.getId().toString());
            String a = i.getId().toString();
            logger.info(a);
            String av = "1000";
            logger.info(av);
            CreateHtmlUtils.CreateHtml(av,fileName);
            //new CreateHtml(String av,String pathname);
        }else {
            return null;
        }
        return null;*/
        try {

            //创建页面


            return new Message(0,"success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(-1,"fail to upload");


        }

    }
}
