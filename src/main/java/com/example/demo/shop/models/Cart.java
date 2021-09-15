package com.example.demo.shop.models;

import com.example.demo.shop.repositories.ProductDao;
import lombok.Data;


@Data
public class Cart {

    private Product product;
    private long quantity;

    public Cart(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }


}
