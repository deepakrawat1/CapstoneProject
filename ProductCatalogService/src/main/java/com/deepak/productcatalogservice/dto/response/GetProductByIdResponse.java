package com.deepak.productcatalogservice.dto.response;

import lombok.Setter;

@Setter
public class GetProductByIdResponse {
    private String name;
    private Double price;
    private String brand;
    private String description;
    private String imageUrl;
    private boolean active;
    private String attributes;
    private String category;
}
