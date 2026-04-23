package com.deepak.productcatalogservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseModel {

    @Column(nullable = false, unique = true, length = 100)
    private String name;
    @Column(length = 200)
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Product> products;

}
