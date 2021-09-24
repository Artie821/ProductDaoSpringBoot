package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartJPAInterface extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c WHERE c.product.id = ?1")
    Cart findByProductId(Long productId);
}
