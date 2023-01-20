package com.onlinestore.service;

import com.onlinestore.dto.CategoryDetailsDto;
import com.onlinestore.dto.CategorySaveDto;
import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;

import java.util.List;

public interface CategoryService {

    public CategorySaveDto createCategory(String name);

    public CategoryDetailsDto getCategory(Long id);

    public void updateCategory(Long id, CategorySaveDto categorySaveDto);

    public List<ProductDetailsDto> findProducts (Long id);
}
