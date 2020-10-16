package com.shubham.model;

import javax.persistence.*;

@Entity
@Table(name="productdetails")
public class Product {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid", updatable = false, nullable = false)
    private Integer pid;

    @Column(name = "product_name",nullable = false)
    private String product_name;

    @Column(name = "product_description")
    private String product_description;

    @Column(name = "product_price",nullable = false)
    private double product_price;

    public Product() {
        super();
    }

    public Product(Integer pid,String product_name, String product_description, double product_price) {
        this.pid=pid;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
