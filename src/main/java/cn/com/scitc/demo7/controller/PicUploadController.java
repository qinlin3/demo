package cn.com.scitc.demo7.controller;

import cn.com.scitc.demo7.utils.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 图片上传
 * @author 吴洋
 *
 */
@Controller
public class PicUploadController {

    @RequestMapping("/picUpload")
    public String picUpload(){
        return "picUpload1";
    }
    @ResponseBody
    @PostMapping("/upload")
    public Object upload(MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        String filePath = "E:/demo10/src/main/resources/static/images/";
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            return new Message(0,"success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(-1,"fail to upload");
        }
    }
}
