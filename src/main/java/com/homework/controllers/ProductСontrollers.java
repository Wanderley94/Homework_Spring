package com.homework.controllers;

import com.homework.model.Product;
import com.homework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductСontrollers {

    private ProductService productService;

    @Autowired
    public ProductСontrollers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showAllProducts(Model model) {
       List<Product> products = productService.getAllProducts();
       model.addAttribute("products", products);
       return "catalog_products";
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Product showJsonStudent(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
        public String saveNewProduct(@ModelAttribute Product newProduct) {
            productService.saveOrUpdateProduct(newProduct);
            return "redirect:/products/";
    }

}
