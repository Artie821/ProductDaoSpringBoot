package com.example.demo.shop.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    List<Cart> cartList;
    Date date;
    BigDecimal cartValue;
    Long orderNumber;
    OrderState orderState;

    public Order(List<Cart> cartList, Date date, BigDecimal cartValue, Long orderNumber, OrderState orderState) {
        this.cartList = cartList;
        this.date = date;
        this.cartValue = cartValue;
        this.orderNumber = orderNumber;
        this.orderState = orderState;
    }

}
