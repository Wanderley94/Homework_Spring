package com.homework.services;

import com.homework.model.Product;
import com.homework.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private ProductRepositories productRepositories;

    @Autowired
    public void setProductRepositories(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    public List<Product> getAllProducts() {
        return productRepositories.findAll();
    }

    public Product findById(Long id){
       return productRepositories.findById(id);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepositories.saveOrUpdateProduct(product);

    }


}
