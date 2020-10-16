package com.shubham.service;

import com.shubham.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();
    Product getProductById(Integer id);
    public void deleteProducts(Integer id);
    public Product saveProduct(Product product);

}
