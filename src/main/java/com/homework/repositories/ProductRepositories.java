package com.homework.repositories;

import com.homework.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepositories {

    private List<Product> listProducts;
    private Long maxId;

    @PostConstruct
    public void init(){

    listProducts = new ArrayList<>();
    listProducts.add(new Product(1L,"Морковь", 300));
    listProducts.add(new Product(2L,"Колбаса", 120));
    this.maxId = 2L;

    }
    public List<Product> findAll() {
        return  Collections.unmodifiableList(listProducts);
    }

    public Product findById(Long id) {

        for (Product element: listProducts) {
            if (element.getId().equals(id)){
                return element;
            }
        }

        throw new RuntimeException("Product not found");

    }

    public Product saveOrUpdateProduct(Product product) {
        if (product.getId() == null) {
            maxId++;
            product.setId(maxId);
            listProducts.add(product);
            return product;
        } else {
            for (int i = 0; i < listProducts.size(); i++) {
                if (listProducts.get(i).getId().equals(product.getId())) {
                    listProducts.set(i, product);
                    return product;
                }
            }
        }

        throw new RuntimeException("Error");
    }
}
