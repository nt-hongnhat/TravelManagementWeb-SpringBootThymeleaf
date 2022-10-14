package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/{locale:en|vi}/admin")
public class AdminController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories(""));
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        String profile = WebUtils.toString(user);
        model.addAttribute("profile", profile);
        return "views/admin/index";
    }
}
