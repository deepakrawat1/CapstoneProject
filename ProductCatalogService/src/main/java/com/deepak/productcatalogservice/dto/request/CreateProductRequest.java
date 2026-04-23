package com.deepak.productcatalogservice.dto.request;

import com.deepak.productcatalogservice.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateProductRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name size must be between 3 and 50")
    private String name;
    @NotBlank(message = "Price is required")
    private Double price;
    @NotBlank(message = "Brand is required")
    private String brand;
    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 200, message = "Description size must be between 20 and 200")
    private String description;
    @NotBlank(message = "Image is required")
    private String imageUrl;
    private boolean active;
    private String attributes;
    @NotBlank(message = "Category is required")
    private String category;
}
