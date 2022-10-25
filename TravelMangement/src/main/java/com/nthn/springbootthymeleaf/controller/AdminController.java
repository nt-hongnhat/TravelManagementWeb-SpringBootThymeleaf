package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.service.impl.CategoryServiceImpl;
import com.nthn.springbootthymeleaf.service.impl.ProvinceServiceImpl;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ProvinceServiceImpl provinceService;
    @Autowired
    private TourService tourService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories(""));
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        User user = (User) ((Authentication) principal).getPrincipal();

        String profile = WebUtils.toString(user);

        model.addAttribute("profile", profile);
        return "views/admin/index";
    }


    @GetMapping("/news")
    public String getNews(Model model) {

        return "views/admin/news";
    }


}
