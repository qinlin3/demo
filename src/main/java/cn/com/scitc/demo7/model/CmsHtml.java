package cn.com.scitc.demo7.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cms_html", schema = "student", catalog = "")
public class CmsHtml {
    private Integer id;
    private String fileName;
    private String pathname;
    private String htmlName;
    private String filename;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "file_name", nullable = false, length = 200)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "pathname", nullable = false, length = 2000)
    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    @Basic
    @Column(name = "html_name", nullable = false, length = 2000)
    public String getHtmlName() {
        return htmlName;
    }

    public void setHtmlName(String htmlName) {
        this.htmlName = htmlName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsHtml cmsHtml = (CmsHtml) o;
        return Objects.equals(id, cmsHtml.id) &&
                Objects.equals(fileName, cmsHtml.fileName) &&
                Objects.equals(pathname, cmsHtml.pathname) &&
                Objects.equals(htmlName, cmsHtml.htmlName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, pathname, htmlName);
    }

    @Basic
    @Column(name = "filename", nullable = false, length = 200)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
