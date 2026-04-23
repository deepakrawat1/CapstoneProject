package com.deepak.productcatalogservice.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
public class GetCategoryByIdResponse {
    private String name;
    private String description;
}
