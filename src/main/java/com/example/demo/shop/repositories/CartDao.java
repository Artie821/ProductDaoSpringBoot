package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.models.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
public class CartDao {

    private final CartJPAInterface cartJPAInterface;

    public CartDao(CartJPAInterface cartJPAInterface) {
        this.cartJPAInterface = cartJPAInterface;
    }

    private List<Cart> cartList = new ArrayList<>();

    public List<Cart> all() {
        return cartList;
    }

    public void addProductToCart(Product product, Long quantity) {
        Cart cart;
        if (quantity > 0) {
            if (!cartList.stream().filter(c -> c.getProduct().getId() == product.getId()).findFirst().isPresent()) {
                cart = new Cart();
                cart.setQuantity(quantity);
                cart.setProduct(product);
                cartList.add(cart);
            } else if (cartList.stream().filter(c -> c.getProduct().getId() == product.getId()).findFirst().isPresent()) {
                cart = cartList.get(cartList.indexOf(cartList.stream().filter(c -> c.getProduct().getId() == product.getId()).findFirst().get()));
                cart.setQuantity(cart.getQuantity() + quantity);
            }
        }
    }


    public void removeCartElement(Long productId) {
        Cart c = cartList.stream().filter(cart -> cart.getProduct().getId() == productId).findFirst().get();
        System.out.println(c);
        cartList.remove(c);
    }

    public void addRemoveOneProduct(Long quantity, Long productId) {
        Cart cart = cartList.stream().filter(c -> c.getProduct().getId() == productId).findFirst().get();
        if (cart.getQuantity() + quantity <= 0) {
            cartList.remove(cart);
        } else {
            cart.setQuantity(cart.getQuantity() + quantity);
        }
    }

}
