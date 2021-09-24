package com.example.demo.shop.controllers;

import com.example.demo.shop.exeptions.EmailAlreadyExistExection;
import com.example.demo.shop.exeptions.UserAlreadyExistException;
import com.example.demo.shop.models.UserEntity;
import com.example.demo.shop.security.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomePageController {

    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/adminOrUser")
    public String adminUser() {
        return "adminUser";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String saveUserWithEmail(@Valid UserEntity user, BindingResult result, Model model, HttpServletRequest request) {

//        String pathInfo = request.getRequestURL().toString().replace("/register","");
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userService.saveUser(user);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("login", true);
            return "register";
        } catch (EmailAlreadyExistExection e) {
            model.addAttribute("email", true);
            return "register";
        }
        return "redirect:/login?success=true";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String registerSuccess(Model model) {
        return "login";
    }



}
