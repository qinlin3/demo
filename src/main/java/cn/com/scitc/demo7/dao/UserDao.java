package cn.com.scitc.demo7.dao;

import cn.com.scitc.demo7.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByName(String name);
}
