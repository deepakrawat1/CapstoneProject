package com.deepak.productcatalogservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateCategoryRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name size must be between 3 and 100")
    private String name;
    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 200, message = "Description size must be between 20 and 200")
    private String description;
}
