package com.onlinestore.controller;

import com.onlinestore.dto.CategoryDetailsDto;
import com.onlinestore.dto.CategorySaveDto;
import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CategorySaveDto createCategory(String name) {
        return categoryService.createCategory(name);
    }

    @GetMapping("/{id}")
    public CategoryDetailsDto getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/update/{id}")
    public void updateCategory(@PathVariable Long id, String name) {
        CategorySaveDto categorySaveDto = new CategorySaveDto(name);
        categoryService.updateCategory(id, categorySaveDto);
    }

    @GetMapping("/{id}/products")
    public List<ProductDetailsDto> getProducts(@PathVariable Long id) {
        return categoryService.findProducts(id);
    }
}
