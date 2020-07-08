package com.homework.repositories;

import com.homework.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByCostGreaterThan(int minCost);
    List<Product> findAllByCostLessThan(int maxCost);
    List<Product> findAllByCostBetween(int minCost, int maxCost);
}
