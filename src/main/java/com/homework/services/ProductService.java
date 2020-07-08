package com.homework.services;

import com.homework.model.Product;
import com.homework.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.net.URLConnection;
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

    public List<Product> findProductsByMinCost(int minCost) {
        return productRepositories.findAllByCostGreaterThan(minCost);
    }

    public List<Product> findProductsByMaxCost(int maxCost) {
        return productRepositories.findAllByCostLessThan(maxCost);
    }

    public List<Product> findProductsByMinOrMaxCost(int minCost, int maxCost) {
        return productRepositories.findAllByCostBetween(minCost,maxCost);
    }

    public Page<Product> findbyPage(int pageNumber, int pageSize){
        return productRepositories.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> findAll(Specification<Product> spec, int pageNumber) {
        return productRepositories.findAll(spec, PageRequest.of(pageNumber - 1, 5));
    }

//
//    public Product findById(Long id){
//       return productRepositories.findById(id);
//    }
//
//    public Product saveOrUpdateProduct(Product product) {
//        return productRepositories.saveOrUpdateProduct(product);
//
//    }


}
