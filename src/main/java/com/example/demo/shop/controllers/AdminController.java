package com.example.demo.shop.controllers;

import com.example.demo.shop.models.OrderState;
import com.example.demo.shop.models.Product;
import com.example.demo.shop.models.ProductCategory;
import com.example.demo.shop.repositories.CategoryDao;
import com.example.demo.shop.repositories.ProductDao;
import com.example.demo.shop.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final OrderService orderService;

    public AdminController(ProductDao productDao, CategoryDao categoryDao, OrderService orderService) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.orderService = orderService;
    }

    @GetMapping (value = "/dashboard/")
    public String adminDashboard(Model model, HttpServletRequest request) {
        model.addAttribute("categories", categoryDao.all());
        return "/admin/adminDashboard";
    }

    @GetMapping (value = "productsAdmin/")
    public String adminProducts(Model model, HttpServletRequest request) {
        model.addAttribute("products", productDao.all());
        return "/admin/products";
    }

    @GetMapping (value = "ordersAdmin/")
    public String adminOrders(Model model, HttpServletRequest request) {
        model.addAttribute("orders", orderService.all());
        return "/admin/orders";
    }

    @GetMapping (value = "addCategoryAdmin/")
    public String adminAddCategory(Model model, HttpServletRequest request) {
        model.addAttribute("productCategories", categoryDao.all());
        return "/admin/addCategory";
    }

    @RequestMapping(value = "addCategoryAdmin/", method = RequestMethod.POST)
    public String adminAddCategoryPost(HttpServletRequest request, Model model, RedirectAttributes redirectAttrs){
        if(categoryDao.alreadyExist(request.getParameter("categoryName"))) {
            redirectAttrs.addAttribute("exist", true);
            return "redirect:/addCategoryAdmin/";
        }
        categoryDao.addCategory(request.getParameter("categoryName"));
        return "redirect:/dashboard/";
    }

    @GetMapping (value = "editCategoryAdmin/{name}")
    public String adminEditCategory(Model model, @PathVariable String name) {
        model.addAttribute("index",categoryDao.getCategoryIndexByName(name));
        model.addAttribute("category", name);
        return "/admin/editCategory";
    }

    @RequestMapping(value = "editCategoryAdmin/", method = RequestMethod.POST)
    public String adminEditCategoryPost(HttpServletRequest request, Model model, RedirectAttributes redirectAttrs){
        if(categoryDao.alreadyExist(request.getParameter("categoryName"))) {
            redirectAttrs.addAttribute("exist", true);
            ProductCategory pc = categoryDao.getCategoryByIndex(Integer.parseInt(request.getParameter("index")));
            return "redirect:/editCategoryAdmin/" + pc.getCategoryName();
        }
        categoryDao.editCategoryByName(Integer.parseInt(request.getParameter("index")), request.getParameter("categoryName"));
        return "redirect:/dashboard/";
    }

    @GetMapping (value = "removeCategoryAdmin/{name}")
    public String adminRemoveCategory(Model model, @PathVariable String name) {
        categoryDao.removeCategoryByName(name);
        return "redirect:/dashboard/";
    }

    @RequestMapping(value = "addProductAdmin/", method = RequestMethod.POST)
    public String adminAddProductPost(@ModelAttribute("product") Product
                                                  product, Model model, RedirectAttributes redirectAttrs){
        model.addAttribute("ProductCategory", productDao.all());
        productDao.addNewProduct(product);
        return "redirect:/productsAdmin/";
    }

    @GetMapping (value = "addProductAdmin/")
    public String adminAddProduct(Model model, @ModelAttribute("product") Product
            product) {
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryDao.all());
        return "/admin/addProduct";
    }

    @GetMapping (value = "editProductAdmin/{name}")
    public String adminEditProduct(Model model, @ModelAttribute("product") Product
            product, @PathVariable String name) {
        model.addAttribute("product", productDao.byName(name));
        model.addAttribute("categories", categoryDao.all());
        model.addAttribute("index",productDao.findIndexByName(name));
        System.out.println(productDao.findIndexByName(name));
        return "/admin/editProduct";
    }

    @RequestMapping(value = "editProductAdmin/", method = RequestMethod.POST)
    public String adminEditProductPost( @ModelAttribute("product") Product
                                              product, Model model, HttpServletRequest request){
        model.addAttribute("ProductCategory", productDao.all());
        System.out.println(request.getParameter("index"));
        productDao.editProductById(Integer.parseInt(request.getParameter("index")) , product);
        return "redirect:/productsAdmin/";
    }

    @GetMapping (value = "removeProductAdmin/{name}")
    public String adminRemoveProduct(Model model, @PathVariable String name) {
        productDao.removeProductByName(name);
        return "redirect:/productsAdmin/";
    }

    @GetMapping("orderStatusSetREALIZACJA/{id}")
    public String orderStatusSetREALIZACJA(@PathVariable int id){
        OrderState o = OrderState.REALIZACJA;
        orderService.changeState(id,o);
        return "redirect:/ordersAdmin/";
    }

    @GetMapping("orderStatusSetANULOWANO/{id}")
    public String orderStatusSetANULOWANO(@PathVariable int id){
        OrderState o = OrderState.ANULOWANO;
        orderService.changeState(id,o);
        return "redirect:/ordersAdmin/";
    }

    @GetMapping("orderStatusSetZREALIZOWANE/{id}")
    public String orderStatusSetZREALIZOWANE(@PathVariable int id){
        OrderState o = OrderState.ZREALIZOWANE;
        orderService.changeState(id,o);
        return "redirect:/ordersAdmin/";
    }

    @GetMapping("/detailsAdmin/{id}")
    public String orderDetails(@PathVariable long id, Model model){
        model.addAttribute("itemsInCart", orderService.OrderBonus(id));
        model.addAttribute("order", orderService.findById(id));
        return "/admin/orderDetails";
    }




}
