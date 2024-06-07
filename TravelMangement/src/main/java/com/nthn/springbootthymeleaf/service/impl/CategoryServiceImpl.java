package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.model.Category;
import com.nthn.springbootthymeleaf.repository.CategoryRepository;
import com.nthn.springbootthymeleaf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        for (Category category : categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))){
            System.out.println(category);
        }
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
