package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Category;

import java.util.List;


public interface CategoryService {
    void create(Category category);

    Category getCategoryById(Integer id);

    List<Category> getCategories(String keyword);

    List<Category> getCategories();

    void update(Integer id, Category category);

    void delete(Integer id);
}
