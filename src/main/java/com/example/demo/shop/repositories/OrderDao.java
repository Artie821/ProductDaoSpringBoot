package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.models.Order;

import com.example.demo.shop.models.OrderState;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDao {

    private List<Order> orderList = new ArrayList<>();

    public List<Order> all() {
        return orderList;
    }

    public void addOrder(List<Cart> all, BigDecimal decimal) {
        Date date = new Date();
        Long orderNumber;
        if(orderList.size()==0){orderNumber = 1l;}
        else {orderNumber = orderList.get(orderList.size()-1).getOrderNumber()+1;}
        OrderState o = OrderState.PRZYJĘTO;
        Order order = new Order(all, date, decimal, orderNumber, o);
        orderList.add(order);
    }

    public void changeState(int id, OrderState state){
        OrderState o = state;
        orderList.get(id).setOrderState(o);
    }
}
