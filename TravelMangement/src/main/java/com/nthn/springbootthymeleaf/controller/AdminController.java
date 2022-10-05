package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private TourService tourService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private WardService wardService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("provinces", provinceService.getAll());
        model.addAttribute("districts", districtService.getAll());
        model.addAttribute("wards", wardService.getAll());
    }

    @GetMapping("/user")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/userList";
    }


}
