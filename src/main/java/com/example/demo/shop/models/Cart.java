package com.example.demo.shop.models;

import lombok.Data;

import java.util.List;

@Data
public class Cart implements Cloneable {

    private Product product;
    private long quantity;

    public Cart(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }


}
