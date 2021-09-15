package com.example.demo.shop.models;

import lombok.Data;

@Data
public class ProductCategory {

    private String categoryName;

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
