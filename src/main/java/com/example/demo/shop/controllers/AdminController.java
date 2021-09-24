package com.example.demo.shop.controllers;

import com.example.demo.shop.models.Product;
import com.example.demo.shop.models.ProductCategory;
import com.example.demo.shop.repositories.CategoryDao;
import com.example.demo.shop.repositories.OrderDao;
import com.example.demo.shop.repositories.ProductDao;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Scope("session")
@Controller
public class AdminController {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final OrderDao orderDao;

    public AdminController(ProductDao productDao, CategoryDao categoryDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.orderDao = orderDao;
    }

    public String loggedUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return username;
    }

    @GetMapping (value = "/admin/dashboard/")
    public String adminDashboard(Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("categories", categoryDao.allActiveAndNotActive());
        return "/admin/adminDashboard";
    }

    @GetMapping (value = "/admin/productsAdmin/")
    public String adminProducts(Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("products", productDao.allActiveAndNotActive());
        return "/admin/products";
    }

    @GetMapping (value = "/admin/ordersAdmin/")
    public String adminOrders(Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("orders", orderDao.all());
        return "/admin/orders";
    }

    @GetMapping (value = "/admin/addCategoryAdmin/")
    public String adminAddCategory(Model model) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("productCategories", categoryDao.all());
        return "/admin/addCategory";
    }

    @RequestMapping(value = "/admin/addCategoryAdmin/", method = RequestMethod.POST)
    public String adminAddCategoryPost(@ModelAttribute("productCategory") ProductCategory
                                                   category){
        categoryDao.addCategory(category);
        return "redirect:/admin/dashboard/";
    }

    @GetMapping (value = "/admin/editCategoryAdmin/{id}")
    public String adminEditCategory(Model model, @PathVariable Long id) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("category", categoryDao.getCategoryById(id));
        return "/admin/editCategory";
    }

    @RequestMapping(value = "/admin/editCategoryAdmin/", method = RequestMethod.POST)
    public String adminEditCategoryPost(@ModelAttribute("productCategory") ProductCategory category){
        categoryDao.addCategory(category);
        return "redirect:/admin/dashboard/";
    }

    @GetMapping (value = "/admin/removeCategoryAdmin/{id}")
    public String adminRemoveCategory(@PathVariable Long id, Model model) {
        model.addAttribute("username", loggedUserName());
        categoryDao.removeCategoryById(id);
        return "redirect:/admin/dashboard/";
    }

    @RequestMapping(value = "/admin/addProductAdmin/", method = RequestMethod.POST)
    public String adminAddProductPost(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("username", loggedUserName());
        model.addAttribute("ProductCategory", productDao.all());
        productDao.addNewProduct(product);
        return "redirect:/admin/productsAdmin/";
    }

    @GetMapping (value = "/admin/addProductAdmin/")
    public String adminAddProduct(Model model, @ModelAttribute("product") Product
            product) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryDao.all());
        return "/admin/addProduct";
    }

    @GetMapping (value = "/admin/editProductAdmin/{id}")
    public String adminEditProduct(Model model, @ModelAttribute("product") Product
            product, @PathVariable Long id) {
        model.addAttribute("username", loggedUserName());
        model.addAttribute("product", productDao.findProductById(id));
        model.addAttribute("categories", categoryDao.all());
        return "/admin/editProduct";
    }

    @RequestMapping(value = "/admin/editProductAdmin/", method = RequestMethod.POST)
    public String adminEditProductPost( @ModelAttribute("product") Product
                                              product, Model model, HttpServletRequest request){
       productDao.editProduct(product);
        return "redirect:/admin/productsAdmin/";
    }

    @GetMapping (value = "/admin/removeProductAdmin/{id}")
    public String adminRemoveProduct(Model model, @PathVariable Long id) {
        model.addAttribute("username", loggedUserName());
        productDao.removeProductByName(id);
        return "redirect:/admin/productsAdmin/";
    }

    @GetMapping("/admin/orderStatusSetREALIZACJA/{id}")
    public String orderStatusSetREALIZACJA(Model model,@PathVariable Long id){
        model.addAttribute("username", loggedUserName());
        orderDao.changeState(id,"REALIZACJA");
        return "redirect:/admin/ordersAdmin/";
    }

    @GetMapping("/admin/orderStatusSetANULOWANO/{id}")
    public String orderStatusSetANULOWANO(Model model,@PathVariable Long id){
        model.addAttribute("username", loggedUserName());
        orderDao.changeState(id,"ANULOWANO");
        return "redirect:/admin/ordersAdmin/";
    }

    @GetMapping("/admin/orderStatusSetZREALIZOWANE/{id}")
    public String orderStatusSetZREALIZOWANE(Model model,@PathVariable Long id){
        model.addAttribute("username", loggedUserName());
        orderDao.changeState(id,"ZREALIZOWANO");
        return "redirect:/admin/ordersAdmin/";
    }

    @GetMapping("/admin/detailsAdmin/{id}")
    public String orderDetails(@PathVariable long id, Model model){
        model.addAttribute("username", loggedUserName());
        model.addAttribute("itemsInCart", orderDao.orderBonus(id));
        model.addAttribute("order", orderDao.findById(id));
        return "/admin/orderDetails";
    }




}
