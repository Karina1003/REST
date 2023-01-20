package com.onlinestore.service;

import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.dto.ProductSaveDto;
import org.springframework.data.domain.Page;

public interface ProductService {
    public ProductSaveDto createProduct(String name, String description, Long categoryId);

    public ProductDetailsDto getProduct(Long id);

    public void updateProduct(Long id, ProductSaveDto productSaveDto);

    public void deleteProduct(ProductDetailsDto productDetailsDto);

    public Page<ProductDetailsDto> findByFilter (int page, int size, String name, String description);
}
