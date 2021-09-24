package com.example.demo.shop.services;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.repositories.CartDao;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class CartService{

    private final CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public List<Cart> all() {
        if (cartDao.all().isEmpty()) {
            return null;
        }
        return cartDao.all();
    }

    public long numberOfItemsInCart(){
       return cartDao.all().size();
    }


    public boolean isCartEmpty(){
        if(cartDao.all().isEmpty()){
            return true;
        }
        return false;
    }

    public boolean threeOrMore(){
        if(cartDao.all().size() >= 3){
            return true;
        }
        return false;
    }

    public BigDecimal totalCartValue(){
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Cart cart: cartDao.all()) {
            BigDecimal d = BigDecimal.valueOf(cart.getQuantity());
            totalValue = totalValue.add(d.multiply(cart.getProduct().getPrice()));
        }
        if(cartDao.all().size() >= 3){

            totalValue = totalValue.multiply(BigDecimal.valueOf(0.95)).setScale(2, BigDecimal.ROUND_UP);
        }
        return totalValue;
    }




}
