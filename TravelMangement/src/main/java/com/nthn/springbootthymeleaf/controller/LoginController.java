package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login(Model model) {
        return "views/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Account accountRegistration = new Account();
        model.addAttribute("accountRegistration", accountRegistration);
        return "views/auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("accountRegistration") Account accountRegistration, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("accountRegistration", accountRegistration);
        if (accountRegistration.getPassword().equals(accountRegistration.getConfirm())) {
            Account accountExists = accountService.getAccount(accountRegistration.getUsername());
            if (accountExists != null || result.hasErrors()) {
                redirectAttributes.addFlashAttribute("accountRegistration", accountRegistration);
                System.out.println(accountRegistration);
                return "redirect:/register?error";
            }

            accountService.register(accountRegistration);
            redirectAttributes.addFlashAttribute("success", "Đăng ký tài khoản thành công!");
            return "redirect:/register?success";
        } else {
            redirectAttributes.addFlashAttribute("accountRegistration", accountRegistration);
            System.out.println(accountRegistration);
            return "redirect:/register?confirm";
        }
    }


    @PostMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() + " You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "views/403";
    }
}
