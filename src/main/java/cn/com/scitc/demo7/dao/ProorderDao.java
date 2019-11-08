package cn.com.scitc.demo7.dao;

import cn.com.scitc.demo7.model.Proorder;
import org.springframework.data.repository.CrudRepository;

public interface ProorderDao extends CrudRepository<Proorder, Integer> {
 //   User findByName(String name);
    Iterable<Proorder> findByName(String name);
}
