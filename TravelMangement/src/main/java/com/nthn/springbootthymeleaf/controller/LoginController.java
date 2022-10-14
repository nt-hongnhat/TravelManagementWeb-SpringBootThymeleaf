package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{locale:en|vi}/login")
    public String login(Model model) {
        return "views/auth/login";
    }

    @GetMapping("/{locale:en|vi}/register")
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
            if (accountExists != null) {
                redirectAttributes.addFlashAttribute("accountRegistration", accountRegistration);
                System.out.println(accountRegistration);

                return "redirect:/register?username";
            }
            if (result.hasErrors()) {
                return "views/auth/register";
            }
            accountService.register(accountRegistration);
            return "redirect:/register?success";
        } else {
            redirectAttributes.addFlashAttribute("accountRegistration", accountRegistration);
            System.out.println(accountRegistration);
            return "redirect:/register?confirm";
        }
    }
}
