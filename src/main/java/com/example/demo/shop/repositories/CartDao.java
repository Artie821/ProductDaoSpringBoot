package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDao {


    private List<Cart> cartList = new ArrayList<>();

    public List<Cart> all() {
        return cartList;
    }

    public void addToCart(Product product, Long quantity) {
        Cart cart = new Cart(product, quantity);
        cartList.add(cart);
    }

    public void removeElement(Product product){
        Cart cart = byProductName(product);
        cartList.remove(cart);
    }

    public Cart byProductName(Product product) {
        for (Cart cart : cartList) {
            if (product.equals(cart.getProduct())) {
                return cart;
            }
        }
        return null;
    }
}
