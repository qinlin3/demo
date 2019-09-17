package cn.com.scitc.demo7.dao;

import cn.com.scitc.demo7.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Integer> {
}
