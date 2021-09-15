package com.example.demo.shop.models;

import lombok.Data;

@Data
public class UserEntity {
    private long id;
    private String userName;
    private String userSurname;
    private Order order;
    private String password;
    private String email;

}
