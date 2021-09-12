package com.example.demo.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products/")
    public String list(Model model) {
        model.addAttribute("products", productDao.all());
        return "list";
    }

    @GetMapping("/products/details/{name}")
    public String productDetails(Model model, @PathVariable String name){
        System.out.println(name);
        model.addAttribute("product", productDao.byName(name));
        return "details";
    }


}
