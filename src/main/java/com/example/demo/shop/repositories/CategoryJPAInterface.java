package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Product;
import com.example.demo.shop.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryJPAInterface extends JpaRepository<ProductCategory, Long> {

    @Query("select c from ProductCategory c WHERE c.active = true")
    List<ProductCategory> findAllActiveCategories();
}
