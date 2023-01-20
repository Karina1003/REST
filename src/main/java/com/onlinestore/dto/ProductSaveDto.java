package com.onlinestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
public class ProductSaveDto {
    @NotBlank (message = "Name of the product must be present")
    private String name;
    private String description;
    @NotNull
    private Long categoryId;
}
