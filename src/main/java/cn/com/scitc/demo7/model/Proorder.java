package cn.com.scitc.demo7.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Proorder {
    private Integer orderId;
    private String name;
    private String product;
    private String price;

    @Id
    @Column(name = "orderId", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
    @Column(name = "price", nullable = false, length = 4)
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
        return Objects.equals(orderId, proorder.orderId) &&
                Objects.equals(name, proorder.name) &&
                Objects.equals(product, proorder.product) &&
                Objects.equals(price, proorder.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, name, product, price);
    }
}
