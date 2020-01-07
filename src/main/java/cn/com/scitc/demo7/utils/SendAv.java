package cn.com.scitc.demo7.utils;


import cn.com.scitc.demo7.dao.ImagesaddressDao;
import cn.com.scitc.demo7.model.Imagesaddress;
import org.springframework.beans.factory.annotation.Autowired;

public class SendAv {
    @Autowired
    private ImagesaddressDao imagesaddressDao;
    public void sendav(String file_Name) {

          //Iterable<Imagesaddress> i =  imagesaddressDao.findByImages(file_Name);
        //String a = i.g
    }
}