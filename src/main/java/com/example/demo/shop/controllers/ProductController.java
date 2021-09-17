package com.example.demo.shop.controllers;

import com.example.demo.shop.models.Product;
import com.example.demo.shop.repositories.ProductDao;
import com.example.demo.shop.services.CartService;
import com.example.demo.shop.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@Controller
public class ProductController {

    private final ProductDao productDao;
    private final CartService cartService;
    private final OrderService orderService;


    public ProductController(ProductDao productDao, CartService cartService, OrderService orderService) {
        this.productDao = productDao;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/products/")
    public String list(Model model) {
        model.addAttribute("products", productDao.all());
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/productsList";
    }

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public String addToCartItem(@ModelAttribute("product") Product
                                        product, Model model, HttpServletRequest request) {
        cartService.addToCart(productDao.byName(product.getName()),
                cartService.parseNumberOfItems(request.getParameter("itemsNumber")));
        return "redirect:/products/";
    }

    @GetMapping("/cart/")
    public String cartList(Model model) {
        model.addAttribute("cart", cartService.all());
        model.addAttribute("emptyCart", cartService.isCartEmpty());
        model.addAttribute("threeOrMore", cartService.threeOrMore());
        model.addAttribute("totalCartValue", cartService.totalCartValue());
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/cartView";
    }

    @RequestMapping(value = "/cart/", method = RequestMethod.POST)
    public String removeFromCartItem(@ModelAttribute("product") Product
                                             product, Model model, HttpServletRequest request) {
        cartService.addToCart(productDao.byName(product.getName()),
                cartService.parseNumberOfItems(request.getParameter("itemsNumber")));
        return "redirect:/cart/";
    }

    @RequestMapping(value = "/cart/removeAll", method = RequestMethod.POST)
    public String removeAllFromCartItem(@ModelAttribute("product") Product
                                                product, Model model) {
        cartService.removeAllOfTypeFromCart(productDao.byName(product.getName()));
        return "redirect:/cart/";
    }

    @GetMapping("/orders/")
    public String userOrders(Model model) {
        model.addAttribute("orders", orderService.all());
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/userOrders";
    }

    @RequestMapping(value = "/orders/", method = RequestMethod.POST)
    public String addOrder(Model model, HttpServletRequest request) {
        orderService.addCartToUserOrders(BigDecimal.valueOf(Double.valueOf(request.getParameter("totalValue"))));
        cartService.all().clear();
        return "user/orderAdded";
    }

    @GetMapping("/details/{id}")
    public String orderDetails(@PathVariable long id, Model model){
        model.addAttribute("bonus", orderService.OrderBonus(id));
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        model.addAttribute("order", orderService.findById(id));
        return "user/orderDetails";
    }

    @GetMapping("/orderAdded")
    public String orderDetails( Model model){
        model.addAttribute("itemsInCart", cartService.numberOfItemsInCart());
        return "user/orderAdded";
    }



}
