package com.example.demo.shop.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String name;
    private String description;
    private BigDecimal price;
    private ProductCategory productCategory;

    public Product(String name, String description, BigDecimal price, ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
    }
}
