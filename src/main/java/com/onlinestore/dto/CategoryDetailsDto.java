package com.onlinestore.dto;

import com.onlinestore.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Jacksonized
public class CategoryDetailsDto {
    private Long id;
    private String name;
    private List<Product> products;
}
