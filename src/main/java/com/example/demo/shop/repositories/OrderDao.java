package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.models.UserOrders;

import com.example.demo.shop.models.OrderState;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDao {

    private final OrderJPAInterface orderJPAInterface;
    private final OrderStateJPAInterface orderStateJPAInterface;
    private final UserEntityJPAInterface userEntityJPAInterface;


    public OrderDao(OrderJPAInterface orderJPAInterface, OrderStateJPAInterface orderStateJPAInterface, UserEntityJPAInterface userEntityJPAInterface) {
        this.orderJPAInterface = orderJPAInterface;
        this.orderStateJPAInterface = orderStateJPAInterface;

        this.userEntityJPAInterface = userEntityJPAInterface;
    }

    public List<UserOrders> all() {
        return orderJPAInterface.findAll();
    }

    public List<UserOrders> allOrdersForUser(String username) {
        return orderJPAInterface.findByName(username);
    }


    public void addOrder(List<Cart> all, BigDecimal decimal, String username) {
        Date date = new Date();
        UserOrders order = new UserOrders();
        OrderState uo = orderStateJPAInterface.findByName("PRZYJĘTO");
        List<Cart> carts =new ArrayList<>();
        for (Cart c: all) {
            carts.add(c);
        }
        order.setUserEntity(userEntityJPAInterface.findByName(username));
        order.setCart(carts);
        order.setDate(date);
        order.setCartValue(decimal);
        order.setOrderState(uo);
        orderJPAInterface.save(order);
        all.clear();
    }

    public void changeState(Long id, String state) {
        UserOrders uo = orderJPAInterface.findById(id).get();
        uo.setOrderState(orderStateJPAInterface.findByName(state));
        orderJPAInterface.save(uo);
    }

    public UserOrders findById(long id){
        return orderJPAInterface.findById(id).get();
    }

    public boolean orderBonus(Long id){
        if(orderJPAInterface.findById(id).get().getCart().size()>=3){
            return true;
        } else return false;
    }
}
