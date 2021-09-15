package com.example.demo.shop.services;

import com.example.demo.shop.models.Order;
import com.example.demo.shop.repositories.OrderDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderService {

    private final OrderDao orderDao;
    private final CartService cartService;


    public OrderService(OrderDao orderDao, CartService cartService) {
        this.orderDao = orderDao;
        this.cartService = cartService;
    }

    public void addCartToUserOrders(BigDecimal value) {
        orderDao.addOrder(cartService.all(), value);
    }

    public List<Order> all(){
        return orderDao.all();
    }
}
