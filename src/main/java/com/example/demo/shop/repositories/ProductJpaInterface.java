package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductJpaInterface extends JpaRepository<Product, Long> {

    @Query("select p from Product p WHERE p.active = true")
    List<Product> findAllActiveProducts();

}
