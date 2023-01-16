package com.onlinestore.service;

import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.repository.CategoryRepository;
import com.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product createProduct (String name, String description, long categoryId) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(()->new NoSuchElementException());
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id).
                orElseThrow(()->new NoSuchElementException("No product found"));
    }

    @Override
    public void updateProduct(Product product) {
        Product productOld = productRepository.findById(product.getId()).
                orElseThrow(()->new NoSuchElementException("No product found for update"));
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }



}
