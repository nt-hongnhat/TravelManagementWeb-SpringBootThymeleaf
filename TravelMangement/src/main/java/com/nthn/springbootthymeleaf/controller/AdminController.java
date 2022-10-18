package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private TourService tourService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories(""));
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
    }

    @GetMapping("")
    public String index(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = (User) ((Authentication) principal).getPrincipal();
        String profile = WebUtils.toString(user);
        model.addAttribute("profile", profile);
        return "views/admin/index";
    }

    @GetMapping("/tours")
    public String getTours(Model model) {
        model.addAttribute("tours", tourService.getTours(""));
        return "views/admin/tourManage";
    }

    @DeleteMapping("/tours/{id}/delete")
    public String deleteTourById(@PathVariable("id") Integer id) {
        tourService.delete(id);
        return "redirect:/admin/tours";
    }
}
