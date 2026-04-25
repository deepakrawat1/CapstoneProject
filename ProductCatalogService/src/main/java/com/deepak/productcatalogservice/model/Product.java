package com.deepak.productcatalogservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseModel {
    @Column(nullable = false, unique = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String brand;
    @Column(length = 200)
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @Column
    private boolean active = true;
    @Column(columnDefinition = "json")
    private String attributes;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
