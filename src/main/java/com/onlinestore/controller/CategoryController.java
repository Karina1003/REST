package com.onlinestore.controller;

import com.onlinestore.entity.Category;
import com.onlinestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(String name) {
        return categoryService.createCategory(name);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/update/{id}")
    public void updateCategory(@PathVariable long id, String name) {
        Category category = categoryService.getCategory(id);
        category.setName(name);
        categoryService.updateCategory(category);
    }
}