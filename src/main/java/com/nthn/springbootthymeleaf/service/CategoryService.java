package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Category;

import java.util.List;


public interface CategoryService {
    void create(Category category);
    
    Category getCategoryById(Integer id);
    
    Category getCategoryByName(String name);
    
    List<Category> getAllCategory();
    
    void update(Category category);
    
    Category updateCategory(Category category);
    
    Category deleteCategory(Integer id);
    
    Category getByLinkStatus(String linkStatus);
    
    List<Category> getCategories(String keyword);
    
    List<Category> getCategories();
    
    void update(Integer id, Category category);
    
    void delete(Integer id);
}
