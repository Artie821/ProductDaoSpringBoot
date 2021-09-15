package com.example.demo.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/adminOrUser")
    public String adminUser() {
        return "adminUser";
    }

}
