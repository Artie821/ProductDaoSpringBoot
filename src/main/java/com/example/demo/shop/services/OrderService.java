package com.example.demo.shop.services;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.models.Order;
import com.example.demo.shop.models.OrderState;
import com.example.demo.shop.repositories.OrderDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        List<Cart> cartList;
        cartList = new ArrayList<Cart>(cartService.all().size());
        for (Cart item : cartService.all()) {cartList.add(item);};
        orderDao.addOrder(cartList, value);
    }


    public List<Order> all(){
        return orderDao.all();
    }

    public Order findById(long id){
        return orderDao.all().stream().filter(order -> order.getOrderNumber().equals(id)).findFirst().get();
    }

    public void changeState(int id, OrderState state){
        OrderState o = state;
        findById(id).setOrderState(o);
    }

    public boolean OrderBonus(long id){
        if(orderDao.all().stream().filter(order -> order.getOrderNumber().equals(id)).findFirst().get().getCartList().size()>=3){
            return true;
        }
        return false;
    }
}
