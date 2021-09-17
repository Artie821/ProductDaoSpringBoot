package com.example.demo.shop.repositories;

import com.example.demo.shop.models.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryDao {

    private List<ProductCategory> categoryList = new ArrayList(Arrays.asList(
            new ProductCategory("owoce"),
            new ProductCategory("warzywa"),
            new ProductCategory("inne"))
    );


    public List<ProductCategory> all() {
        return categoryList;
    }

    public ProductCategory byName(String name) {
        for (ProductCategory category : categoryList) {
            if (name.equals(category.getCategoryName())) {
                return category;
            }
        }
        return null;
    }

    public boolean alreadyExist(String name) {
        for (ProductCategory category : categoryList) {
            if (name.equals(category.getCategoryName())) {
                return true;
            }
        }
        return false;
    }

    public ProductCategory getCategoryByIndex(int index){
       return categoryList.get(index);
    }

    public void addCategory(String categoryName){
        categoryList.add(new ProductCategory(categoryName));
    }

    public int getCategoryIndexByName(String name){
        ProductCategory pc = byName(name);
        return categoryList.indexOf(pc);
    }

    public void editCategoryByName(int index, String name){
        categoryList.get(index).setCategoryName(name);
    }

    public void removeCategoryByName(String name){
        ProductCategory pc = byName(name);
        categoryList.remove(pc);
    }
}
