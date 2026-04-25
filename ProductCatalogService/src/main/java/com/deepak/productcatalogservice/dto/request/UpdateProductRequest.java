package com.deepak.productcatalogservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateProductRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name size must be between 3 and 50")
    private String name;
    @NotBlank(message = "Price is required")
    private Double price;
    @NotBlank(message = "Brand is required")
    private String brand;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Image is required")
    private String imageUrl;
    private boolean active;
    private String attributes;
    @NotNull(message = "Category is required")
    private Long category;
}
