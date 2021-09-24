package com.example.demo.shop.security;


import com.example.demo.shop.exeptions.EmailAlreadyExistExection;
import com.example.demo.shop.exeptions.UserAlreadyExistException;
import com.example.demo.shop.models.UserEntity;

public interface UserService {

    UserEntity findByUserName(String name);

    void saveUser(UserEntity user) throws UserAlreadyExistException, EmailAlreadyExistExection;


    boolean checkIfUserExist(String email);

    boolean checkIfUserExistLogin(String login);

    void saveAdmin(UserEntity user) throws UserAlreadyExistException, EmailAlreadyExistExection;

}