package com.onlinestore.service;

import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("No such category"));
    }

    @Override
    public void updateCategory(Category category) {
        Category categoryOld = categoryRepository.findById(category.getId()).
                orElseThrow(()-> new NoSuchElementException("No such category"));
        categoryRepository.save(category);
    }

    //TODO: throw products to other category or delete method
    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Product> findProducts(long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("No such category"));
        return category.getProducts();
    }
}
