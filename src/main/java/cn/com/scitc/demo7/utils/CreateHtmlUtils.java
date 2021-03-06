package cn.com.scitc.demo7.utils;

import cn.com.scitc.demo7.controller.HomeController;
import cn.com.scitc.demo7.dao.ImagesaddressDao;
import cn.com.scitc.demo7.model.Imagesaddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: CreateHtmlUtils
 * @Description: Java 根据模板创建 html
 * @author
 * @date 2016年4月22日 下午3:51:16
 */
public class CreateHtmlUtils {
    private Logger logger = LoggerFactory.getLogger(HomeController.class.getSimpleName());
    @Autowired
    private ImagesaddressDao imagesaddressDao;
    public static void CreateHtml(String pathname,String name) {
       // public static void main(String[] args)
            String filePath = "E:\\demo10\\src\\main\\resources\\templates\\template.html";
            String imagePath = pathname;
                    //"http://localhost:8080/images/d6f36392-5934-4004-b1db-e09959352424.png";
            String disrPath = "E:\\demo10\\src\\main\\resources\\static\\html\\";


            //MakeHtml(filePath,imagePath,disrPath,fileName);

            MakeHtml(filePath,imagePath,disrPath,name);


    }
    /*public String makehtml(String file_name) {
        //Iterable<Imagesaddress> list = imagesaddressDao.findAll();
        Imagesaddress i = imagesaddressDao.findByImages(file_name);
        String a = i.getId().toString();

        logger.info(a);
        return a;
        //return "studentfrom";
    }*/


    /**
     * @Title: MakeHtml
     * @Description: 创建html
     * @param    filePath 设定模板文件
     * @param    imagePath 需要显示图片的路径
     * @param    disrPath  生成html的存放路径
     * @param    fileName  生成html名字
     * @return void    返回类型
     * @throws
     */
    public static void MakeHtml(String filePath,String imagePath,String disrPath,String fileName ){
        try {
            String title = "<image src="+'"'+imagePath+'"'+"/>";
            System.out.print(filePath);
            String templateContent = "";
            FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();
            templateContent = new String(bytes);
            System.out.print(templateContent);
            templateContent = templateContent.replaceAll("###title###", title);
            System.out.print(templateContent);

            String fileame = fileName + ".html";
            fileame = disrPath+"/" + fileame;// 生成的html文件保存路径。
            FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
            System.out.print("文件输出路径:");
            System.out.print(fileame);
            byte tag_bytes[] = templateContent.getBytes();
            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }


}
