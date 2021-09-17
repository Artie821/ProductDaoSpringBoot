package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Product;
import com.example.demo.shop.models.ProductCategory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {


   private final CategoryDao categoryDao = new CategoryDao();

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("Banan", "Żółty", new BigDecimal("25.00"), categoryDao.byName("owoce") ),
            new Product("Ziemniak", "Zimowe", new BigDecimal("99.99"), categoryDao.byName("warzywa")),
            new Product("Chleb", "Razowy", new BigDecimal("5.50"), categoryDao.byName("inne") )));


    public List<Product> all() {
        return products;
    }

    public Product byName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

    public void addNewProduct(Product product){
        products.add(product);
    }

    public void editProductById(int index, Product product){
        products.get(index).setDescription(product.getDescription());
        products.get(index).setName(product.getName());
        products.get(index).setProductCategory(product.getProductCategory());
        products.get(index).setPrice(product.getPrice());
    }

    public Product findProductByIndex(int index){
        return products.get(index);
    }

    public int findIndexByName(String name){
        Product p = byName(name);
        return products.indexOf(p);
    }

    public void removeProductByName(String name){
        Product p = byName(name);
        products.remove(p);
    }

}
