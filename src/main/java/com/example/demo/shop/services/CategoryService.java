package com.example.demo.shop.services;

import com.example.demo.shop.repositories.CategoryDao;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void addCategory(String catName){
        categoryDao.addCategory(catName);
    }
}
