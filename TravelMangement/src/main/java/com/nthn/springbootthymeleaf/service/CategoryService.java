package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    public void save(Category category);
    public void deleteById(int id);
}
