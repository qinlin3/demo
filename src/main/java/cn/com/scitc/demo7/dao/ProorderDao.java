package cn.com.scitc.demo7.dao;

import cn.com.scitc.demo7.model.Proorder;
import cn.com.scitc.demo7.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ProorderDao extends CrudRepository<Proorder, Integer> {
    User findByName(String name);
}
