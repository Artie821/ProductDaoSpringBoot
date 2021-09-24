package com.example.demo.shop.repositories;


import com.example.demo.shop.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserEntityJPAInterface extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where u.username = ?1")
    UserEntity findByName(String name);
}
