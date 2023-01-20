package com.onlinestore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDetailsDto {
    private Long id;
    private String name;
    private String description;
    private IdentifiedName category;
}
