package com.ved.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data // Generates Getters, Setters, toString, etc.
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    private String description;
}