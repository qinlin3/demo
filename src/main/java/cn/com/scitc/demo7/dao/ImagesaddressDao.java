package cn.com.scitc.demo7.dao;

import cn.com.scitc.demo7.model.Imagesaddress;
import cn.com.scitc.demo7.model.Proorder;
import org.springframework.data.repository.CrudRepository;

public interface ImagesaddressDao extends CrudRepository<Imagesaddress, Integer> {
 //   User findByName(String name);
    Iterable<Imagesaddress> findByImages(String images);
}
