package com.onlinestore.controller;

import com.onlinestore.entity.Product;
import com.onlinestore.service.CategoryService;
import com.onlinestore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid String name, String description, Long categoryId) {
        System.out.println("Controller " + name + " " + description );
        System.out.println(categoryId);
        return productService.createProduct(name, description, categoryId);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable Long id, @Valid String name, String description, Long categoryId) {
        Product product = productService.getProduct(id);
        product.setName(name);
        product.setDescription(description);
        product.setCategory(categoryService.getCategory(categoryId));
        productService.updateProduct(productService.getProduct(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(productService.getProduct(id));
    }

    @GetMapping("/list")
    public Page<Product> findByFilter (@RequestParam int page, @RequestParam int size,
                                       @RequestParam String name, @RequestParam String description) {
            return productService.findByFilter(page, size, name, description);
    }
}
