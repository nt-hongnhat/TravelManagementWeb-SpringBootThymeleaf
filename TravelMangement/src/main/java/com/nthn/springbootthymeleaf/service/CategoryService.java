package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Category;
import com.nthn.springbootthymeleaf.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // CREATE
    public void create(Category category) {
        categoryRepository.save(category);
    }

    // READ
    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public List<Category> getCategories(String keyword) {
        if (keyword == null) {
            return categoryRepository.findAll();
        } else return categoryRepository.findAllByNameContainingIgnoreCase(keyword);
    }


    // UPDATE
    public void update(Integer id, Category category) {
        Category original = getCategoryById(id);
        BeanUtils.copyProperties(category, original);
        categoryRepository.save(original);
    }

    // DELETE
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }


    private Category requireOne(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
