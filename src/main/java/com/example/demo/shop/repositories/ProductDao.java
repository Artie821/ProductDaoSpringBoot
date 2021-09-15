package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Product;
import com.example.demo.shop.models.ProductCategory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {

    private List<Product> products = Arrays.asList(
            new Product("Banan", "Żółty", new BigDecimal("25.00"), new ProductCategory("owoce")),
            new Product("Ziemniak", "Zimowe", new BigDecimal("99.99"), new ProductCategory("warzywa")),
            new Product("Chleb", "Razowy", new BigDecimal("5.50"), new ProductCategory("inne")));

    public List<Product> all() {
        return products;
    }

    public Product byName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

}
