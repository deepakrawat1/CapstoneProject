package com.deepak.productcatalogservice.dto.request;

import com.deepak.productcatalogservice.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateProductRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name size must be between 3 and 50")
    private String name;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than or equal to 1")
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
    @NotNull(message = "Category is required")
    private Long category;
}
