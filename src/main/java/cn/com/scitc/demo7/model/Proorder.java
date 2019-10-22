package cn.com.scitc.demo7.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Proorder {
    private Integer id;
    private String name;
    private String product;
    private String price;
    private Timestamp date;

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

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
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "product", nullable = false, length = 20)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Basic
    @Column(name = "price", nullable = false, length = 20)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proorder proorder = (Proorder) o;
        return Objects.equals(id, proorder.id) &&
                Objects.equals(name, proorder.name) &&
                Objects.equals(product, proorder.product) &&
                Objects.equals(price, proorder.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, product, price);
    }


}
