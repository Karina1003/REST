package com.onlinestore.service;

import com.onlinestore.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(String name, String description, Long categoryId);

    public Product getProduct(Long id);

    public void updateProduct(Product product);

    public void deleteProduct(Product product);

    public Page<Product> findByFilter (int page, int size, String name, String description);
}
