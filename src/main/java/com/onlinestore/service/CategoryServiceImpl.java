package com.onlinestore.service;

import com.onlinestore.dto.CategoryDetailsDto;
import com.onlinestore.dto.CategorySaveDto;
import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategorySaveDto createCategory(String name) {
        CategorySaveDto categorySaveDto = new CategorySaveDto(name);
        categoryRepository.save(new Category(categorySaveDto.getName()));
        return categorySaveDto;
    }

    @Override
    @Transactional
    public CategoryDetailsDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("No such category"));
        return CategoryDetailsDto.builder()
                .id(category.getId())
                .name(category.getName())
                .products(category.getProducts())
                .build();
    }

    @Override
    @Transactional
    public void updateCategory(Long id, CategorySaveDto categorySaveDto) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("No such category"));
        category.setName(categorySaveDto.getName());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public List<ProductDetailsDto> findProducts(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("No such category"));
        return convertListToDetailsDto(category.getProducts());
    }

    private List<ProductDetailsDto> convertListToDetailsDto(List<Product> listToConvert) {
        List<ProductDetailsDto> listConverted = new ArrayList<>();
        for (Product product:listToConvert) {
            listConverted.add(ProductDetailsDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .category(ProductServiceImpl.returnIdentifiedName(product.getCategory()))
                            .build());
        }
        return listConverted;
    }
}
