package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.UserDTO;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.UserRoleService;
import com.nthn.springbootthymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("provinces", this.provinceService.getAll());
    }

    @GetMapping
    public String index(Model model) {
        return "views/index";
    }


    @GetMapping("/login")
    public String login() {
        return "views/login";
    }


    @GetMapping("/register")
    public String register(UserDTO userDTO) {
        return "views/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "views/register";
        userService.save(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about() {
        return "views/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "views/contact";
    }

}
