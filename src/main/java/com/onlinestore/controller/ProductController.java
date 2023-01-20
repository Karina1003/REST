package com.onlinestore.controller;

import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.dto.ProductSaveDto;
import com.onlinestore.service.CategoryService;
import com.onlinestore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductSaveDto createProduct(@Valid String name, String description, @Valid Long categoryId) {
        return productService.createProduct(name, description, categoryId);
    }

    @GetMapping("/{id}")
    public ProductDetailsDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable @Valid Long id, @Valid String name, String description, @Valid Long categoryId) {
        ProductSaveDto productSaveDto = new ProductSaveDto(name, description, categoryId);
        productService.updateProduct(id, productSaveDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(productService.getProduct(id));
    }

    @GetMapping("/list")
    public Page<ProductDetailsDto> findByFilter (@RequestParam int page, @RequestParam int size,
                                       @RequestParam String name, @RequestParam String description) {
            return productService.findByFilter(page, size, name, description);
    }
}
