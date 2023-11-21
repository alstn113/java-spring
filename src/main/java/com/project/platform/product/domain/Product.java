package com.project.platform.product.domain;

import com.project.platform.global.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;

    @Enumerated(value = EnumType.STRING)
    private ProductSubCategory subCategory;

    protected Product() {
    }

    public Product(Long id, String name, Long price, ProductCategory category, ProductSubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Product(String name, Long price, ProductCategory category, ProductSubCategory subCategory) {
        this(null, name, price, category, subCategory);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public ProductSubCategory getSubCategory() {
        return subCategory;
    }
}
