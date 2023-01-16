package com.onlinestore.service;

import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name);

    public Category getCategory(long id);

    public void updateCategory(Category category);

    public void deleteCategory(Category category);
    public List<Product> findProducts (long id);
}
