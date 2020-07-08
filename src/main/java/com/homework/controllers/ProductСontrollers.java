package com.homework.controllers;

import com.homework.model.Product;
import com.homework.repositories.specifications.ProductSpecifications;
import com.homework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/products")
public class ProductСontrollers {

    private ProductService productService;

    public ProductСontrollers(){
    }

    @Autowired
    public ProductСontrollers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showAllProducts(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_Cost", required = false) Integer minCost,
                                  @RequestParam(name = "max_Cost", required = false) Integer maxCost) {

        Specification<Product> spec = Specification.where(null);

        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGEThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLEThan(maxCost));
        }


       List<Product> products = productService.findAll(spec, pageNumber).getContent() ;
       model.addAttribute("products", products);
       return "catalog_products";
    }

    @GetMapping("/find_by_min_cost")
    @ResponseBody
    public  List<Product> findProductsByMinCost (@RequestParam int minCost) {
        return productService.findProductsByMinCost(minCost);
    }

    @GetMapping("/find_by_max_cost")
    @ResponseBody
    public  List<Product> findProductsByMaxCost (@RequestParam int maxCost) {
        return productService.findProductsByMaxCost(maxCost);
    }

    @GetMapping("/find_by_min_or_max_cost")
    @ResponseBody
    public  List<Product> findProductsByMinOrMaxCost (@RequestParam int minCost, int maxCost) {
        return productService.findProductsByMinOrMaxCost(minCost,maxCost);
    }



//    @GetMapping("/json/{id}")
//    @ResponseBody
//    public Product showJsonStudent(@PathVariable Long id) {
//        return null; //productService.findById(id);
//    }
//
//    @GetMapping("/add")
//    public String showAddForm() {
//        return "add_product_form";
//    }
//
//    @PostMapping("/add")
//        public String saveNewProduct(@ModelAttribute Product newProduct) {
////            productService.saveOrUpdateProduct(newProduct);
//              return null; //return "redirect:/products/";
//    }

}
