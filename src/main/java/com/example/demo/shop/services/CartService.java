package com.example.demo.shop.services;

import com.example.demo.shop.models.Cart;
import com.example.demo.shop.models.Product;
import com.example.demo.shop.repositories.CartDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    private final CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void addToCart(Product product, Long quantity) {
        if(quantity == 0){
            return;
        }
        if (!(containsProduct(cartDao.all(), product))) {
            cartDao.addToCart(product, quantity);
        } else {
            cartDao.all().stream()
                    .filter(o -> o.getProduct().equals(product))
                    .findFirst().get().setQuantity(cartDao.all().stream()
                    .filter(o -> o.getProduct().equals(product))
                    .findFirst().get().getQuantity() + quantity);
            if(cartDao.all().stream()
                    .filter(o -> o.getProduct().equals(product))
                    .findFirst().get().getQuantity() <= 0l){
                cartDao.removeElement(product);
            }
        }
    }

    public void removeAllOfTypeFromCart(Product product){
        cartDao.removeElement(product);
    }

    public List<Cart> all() {
        if (cartDao.all().isEmpty()) {
            return null;
        }
        return cartDao.all();
    }

    public boolean containsProduct(final List<Cart> list, final Product product) {
        return list.stream().filter(o -> o.getProduct().equals(product)).findFirst().isPresent();
    }

    public long numberOfItemsInCart(){
       return cartDao.all().size();
    }

    public long parseNumberOfItems(String parseVal){
        Long itemsNumber = 0l;
        try{
            itemsNumber = Long.parseLong(parseVal);
        } catch (NumberFormatException e){
            System.out.println("Not a number!");
        }
        return itemsNumber;
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
