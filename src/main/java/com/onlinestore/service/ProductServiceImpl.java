package com.onlinestore.service;

import com.onlinestore.dto.IdentifiedName;
import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.dto.ProductSaveDto;
import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.repository.CategoryRepository;
import com.onlinestore.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductSaveDto createProduct (String name, String description, Long categoryId) {
        ProductSaveDto productSaveDto = new ProductSaveDto(name, description, categoryId);
        Category category = categoryRepository.findById(productSaveDto.getCategoryId()).
                orElseThrow(()->new NoSuchElementException("No group with such id"));
        productRepository.save(new Product(productSaveDto.getName(), productSaveDto.getDescription(), category));
        return productSaveDto;
    }

    @Override
    @Transactional
    public ProductDetailsDto getProduct(Long id) {
         Product product = productRepository.findById(id)
                         .orElseThrow(()->new NoSuchElementException("No product found"));
         return convertToDetailsDto(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long id, ProductSaveDto productSaveDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("No product found"));
        product.setName(productSaveDto.getName());
        product.setDescription(productSaveDto.getDescription());
        Category category = categoryRepository.findById(productSaveDto.getCategoryId()).
                orElseThrow(()->new NoSuchElementException("No group with such id"));
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(ProductDetailsDto productDetailsDto) {
        Product product = productRepository.findById(productDetailsDto.getId())
                .orElseThrow(()->new NoSuchElementException("No product found for deletion"));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public Page<ProductDetailsDto> findByFilter (int page, int size, String name, String description) {
        Pageable searchPage = PageRequest.of(page,size);
        Page<Product> pageProduct = null;
        if (name != null && !name.equals("") && description != null && !description.equals("")) {
            pageProduct = productRepository.findAllByNameAndDescription(searchPage, name, description);
        } else if ((name == null || name.equals("")) &&
                   (description != null && !description.equals(""))) {
            pageProduct = productRepository.findAllByDescription(searchPage, description);
        } else pageProduct = productRepository.findAllByName(searchPage, name);
        return pageProduct.map(this::convertToDetailsDto);
    }

    public static IdentifiedName returnIdentifiedName(Category category) {
        if (category == null) {
            return null;
        } else return new IdentifiedName(category.getId(), category.getName());
    }

    private ProductDetailsDto convertToDetailsDto(Product product) {
        return ProductDetailsDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(returnIdentifiedName(product.getCategory()))
                .build();
    }

}
