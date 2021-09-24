package com.example.demo.shop.repositories;

import com.example.demo.shop.models.UserEntity;
import com.example.demo.shop.models.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderJPAInterface extends JpaRepository<UserOrders, Long> {

    @Query("select o from UserOrders o where o.userEntity.username = ?1")
    List<UserOrders> findByName(String name);
}
