package cn.com.scitc.demo7.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Imagesaddress {
    private Integer id;
    private String images;
    private String address;

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
    @Column(name = "images", nullable = false, length = 2000)
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagesaddress that = (Imagesaddress) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(images, that.images) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, images, address);
    }
}
