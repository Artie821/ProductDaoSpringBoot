package com.example.demo.shop.repositories;

import com.example.demo.shop.models.ProductCategory;
import com.example.demo.shop.models.UserOrders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDao {

    private final CategoryJPAInterface categoryJPAInterface;

    public CategoryDao(CategoryJPAInterface categoryJPAInterface) {
        this.categoryJPAInterface = categoryJPAInterface;
    }

    public List<ProductCategory> all() {
        return categoryJPAInterface.findAllActiveCategories();
    }

    public List<ProductCategory> allActiveAndNotActive() {
        return categoryJPAInterface.findAll();
    }

    public void addCategory(ProductCategory productCategory) {
        productCategory.setActive(true);
        categoryJPAInterface.save(productCategory);
    }

    public ProductCategory getCategoryById(Long id) {
        return categoryJPAInterface.getById(id);
    }

    public void removeCategoryById(Long id) {
        ProductCategory productCategory = categoryJPAInterface.getById(id);
        if(productCategory.getActive()==true) {
           productCategory.setActive(false);
        } else {
            productCategory.setActive(true);
        }
        categoryJPAInterface.save(productCategory);

    }
}
