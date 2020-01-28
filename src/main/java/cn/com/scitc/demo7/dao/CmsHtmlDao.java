package cn.com.scitc.demo7.dao;

import cn.com.scitc.demo7.model.CmsHtml;
import cn.com.scitc.demo7.model.Imagesaddress;
import org.springframework.data.repository.CrudRepository;

public interface CmsHtmlDao extends CrudRepository<CmsHtml, Integer> {
 //   User findByName(String name);
   // CmsHtml findByfile_name(String file_name);
    CmsHtml findByHtmlName(String html_name);
}
