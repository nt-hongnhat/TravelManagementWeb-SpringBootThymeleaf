package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Category;
import com.nthn.springbootthymeleaf.repository.CategoryRepository;
import com.nthn.springbootthymeleaf.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    // READ
    @Override
    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
    
    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    @Override
    public List<Category> getAllCategory() {
        return List.of();
    }
    
    @Override
    public void update(Category category) {
    
    }
    
    @Override
    public Category updateCategory(Category category) {
        return null;
    }
    
    @Override
    public Category deleteCategory(Integer id) {
        return null;
    }
    
    @Override
    public Category getByLinkStatus(String linkStatus) {
        return categoryRepository.findByLinkStatic(linkStatus);
    }
    
    
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    
    @Override
    public List<Category> getCategories(String keyword) {
        return categoryRepository.findAllByNameContainingIgnoreCase(keyword);
    }
    
    // CREATE
    @Override
    public void create(Category category) {
        categoryRepository.save(category);
    }
    
    
    // UPDATE
    @Override
    public void update(Integer id, Category category) {
        Category original = getCategoryById(id);
        BeanUtils.copyProperties(category, original);
        categoryRepository.save(original);
    }
    
    // DELETE
    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
    
    
    private Category requireOne(Integer id) {
        return categoryRepository.findById(id)
                                 .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
