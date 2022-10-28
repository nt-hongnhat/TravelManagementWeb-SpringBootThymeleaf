package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Category;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.TourGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TourGroupService tourGroupService;

    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);

        return "views/admin/categories/list";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request) {
        Category category = new Category();
        category.setName(request.getParameter("categoryName").trim());
        category.setName(request.getParameter("linkStatic").trim().toLowerCase());
        category.setName(request.getParameter("description").trim());
        categoryService.create(category);
        return "redirect:/admin/categories";
    }
}
