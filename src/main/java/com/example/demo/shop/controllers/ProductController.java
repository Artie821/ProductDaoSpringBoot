package com.example.demo.shop.controllers;

import com.example.demo.shop.models.Product;
import com.example.demo.shop.repositories.CartDao;
import com.example.demo.shop.repositories.OrderDao;
import com.example.demo.shop.repositories.ProductDao;
import com.example.demo.shop.services.CartService;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@Scope("session")
@Controller
public class ProductController {

    private final ProductDao productDao;
    private final CartService cartService;
    private final CartDao cartDao;
    private final OrderDao orderDao;


    public ProductController(ProductDao productDao, CartService cartService, CartDao cartDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.cartService = cartService;
        this.cartDao = cartDao;
        this.orderDao = orderDao;
    }

    public String loggedUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return username;
    }

    @GetMapping("/user/products/")
    public String list(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard/";
        }
        model.addAttribute("username", loggedUserName());
        model.addAttribute("products", productDao.all());
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/productsList";
    }

    @RequestMapping(value = "/user/products/", method = RequestMethod.POST)
    public String addToCartItem(@ModelAttribute("product") Product product, HttpServletRequest request) {
        cartDao.addProductToCart(product, Long.parseLong(request.getParameter("itemsNumber")));
        return "redirect:/user/products/";
    }

    @Scope("session")
    @GetMapping("/user/cart/")
    public String cartList(Model model, @ModelAttribute("product") Product
            product) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("cart", cartService.all());
        model.addAttribute("emptyCart", cartService.isCartEmpty());
        model.addAttribute("threeOrMore", cartService.threeOrMore());
        model.addAttribute("totalCartValue", cartService.totalCartValue());
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/cartView";
    }

    @Scope("session")
    @RequestMapping(value = "/user/cart/", method = RequestMethod.POST)
    public String removeFromCartItem(@ModelAttribute("product") Product
                                             product, HttpServletRequest request) {
        cartDao.addRemoveOneProduct(Long.parseLong(request.getParameter("itemsNumber")), product.getId());
        return "redirect:/user/cart/";
    }

    @Scope("session")
    @RequestMapping(value = "/user/cart/removeAll", method = RequestMethod.POST)
    public String removeAllFromCartItem(@ModelAttribute("product") Product
                                                product, Model model) {
        cartDao.removeCartElement(product.getId());
        return "redirect:/user/cart/";
    }

    @GetMapping("/user/orders/")
    public String userOrders(Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("orders", orderDao.allOrdersForUser(loggedUserName()));
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/userOrders";
    }

    @RequestMapping(value = "/user/orders/", method = RequestMethod.POST)
    public String addOrder(Model model, HttpServletRequest request) {
        model.addAttribute("username", loggedUserName());

        orderDao.addOrder(cartDao.all(), BigDecimal.valueOf(Double.valueOf(request.getParameter("totalValue"))), loggedUserName());
        return "user/orderAdded";
    }

    @GetMapping("/user/details/{id}")
    public String orderDetails(@PathVariable long id, Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("bonus", orderDao.orderBonus(id));
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        model.addAttribute("order", orderDao.findById(id));
        return "user/orderDetails";
    }

    @GetMapping("/user/orderAdded")
    public String orderDetails(Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/orderAdded";
    }


}
