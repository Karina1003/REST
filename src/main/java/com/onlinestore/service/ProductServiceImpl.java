package com.onlinestore.service;

import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.repository.CategoryRepository;
import com.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product createProduct (String name, String description, Long categoryId) {
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
    public Product getProduct(Long id) {
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

    @Override
    public Page<Product> findByFilter (int page, int size, String name, String description) {
        Pageable searchPage = PageRequest.of(page,size);
        if (name != null && !name.equals("") && description != null && !description.equals("")) {
            return productRepository.findAllByNameAndDescription(searchPage, name, description);
        } else if ((name == null || name.equals("")) &&
                   (description != null && !description.equals(""))) {
            return productRepository.findAllByDescription(searchPage, description);
        } else return productRepository.findAllByName(searchPage, name);
    }

}
