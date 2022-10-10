package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.AccountDTO;
import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private NewsService newsService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories(""));
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
        model.addAttribute("destinations", this.destinationService.getDestinations(""));
    }

    @GetMapping
    public String index(Model model) {
        return "views/index";
    }

    // GET: /login
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "views/login";
    }


    // GET: /register
    @GetMapping("/register")
    public String register(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPermissionId(1);
        model.addAttribute("account", accountDTO);
        return "views/register";
    }

    // Lưu thông tin đăng ký
    @PostMapping("/register")
    public String register(Model model, @Validated AccountDTO accountDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        // Validate result
        if (result.hasErrors())
            return "views/register";

        try {
            Account newAccount = accountService.create(accountDTO);
        } catch (Exception exception) {
            model.addAttribute("errorMessage", "Error" + exception.getMessage());
            return "views/register";
        }

        redirectAttributes.addFlashAttribute("success", "Saved account successfully!");
        return "redirect:/login";
    }

    // Đăng xuất
    // GET: logout
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User user = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(user);
        model.addAttribute("userInfo", userInfo);

        return "views/profile";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        String profile = WebUtils.toString(user);
        model.addAttribute("profile", profile);
        return "views/admin";
    }
//
//    @RequestMapping(value = "/403", method = RequestMethod.GET)
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//            String userInfo = WebUtils.toString(loginedUser);
//
//            model.addAttribute("userInfo", userInfo);
//
//            String message = "Hi " + principal.getName() //
//                    + "<br> You do not have permission to access this page!";
//            model.addAttribute("message", message);
//
//        }
//
//        return "403Page";
//    }

    @GetMapping("/about")
    public String about(Model model) {
        return "views/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "views/contact";
    }

    @GetMapping("/services")
    public String services(Model model) {
        return "views/services";
    }


}
