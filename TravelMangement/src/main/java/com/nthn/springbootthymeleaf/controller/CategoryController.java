package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.CategoryDTO;
import com.nthn.springbootthymeleaf.VO.CategoryQueryVO;
import com.nthn.springbootthymeleaf.VO.CategoryUpdateVO;
import com.nthn.springbootthymeleaf.VO.CategoryVO;
import com.nthn.springbootthymeleaf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public String save(@Valid @RequestBody CategoryVO vO) {
        return categoryService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CategoryUpdateVO vO) {
        categoryService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public Page<CategoryDTO> query(@Valid CategoryQueryVO vO) {
        return categoryService.query(vO);
    }
}
