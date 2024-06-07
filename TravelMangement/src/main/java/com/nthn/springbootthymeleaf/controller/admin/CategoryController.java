package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.model.Account;
import com.nthn.springbootthymeleaf.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dashboard/categories")
public class CategoryController {
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private AccountService accountService;
//    @Autowired
//    private ProvinceService provinceService;
//
//    @ModelAttribute
//    public void commonAttributes(Model model, HttpSession httpSession) {
//        User currentUser = (User) httpSession.getAttribute("currentUser");
//
//        Account account = accountService.getAccountByUsername(currentUser.getUsername());
//        model.addAttribute("categories", this.categoryService.getCategories(""));
//        model.addAttribute("provinces", this.provinceService.getProvinces(""));
//        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
////        model.addAttribute("avatar", account.getPhotosImagePath());
//    }
//
//    @GetMapping
//    public String index(Model model) {
//        List<Category> categories = categoryService.getCategories();
//
//        model.addAttribute("categories", categories);
//
//        return "views/admin/categories/list";
//    }
//
//    @PostMapping("/create")
//    public String create(HttpServletRequest request) {
//        Category category = new Category();
//        category.setName(request.getParameter("categoryName").trim());
//        category.setName(request.getParameter("linkStatic").trim().toLowerCase());
//        category.setName(request.getParameter("description").trim());
//        categoryService.create(category);
//        return "redirect:/admin/categories";
//    }
}
