package com.nthn.springbootthymeleaf.controller.auth;

import com.nthn.springbootthymeleaf.utils.WebUtils;
import com.nthn.springbootthymeleaf.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@Controller
public class LoginController {
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private AccountValidator accountValidator;
//
//    @InitBinder
//    protected void initBinder(WebDataBinder webDataBinder) {
//        Object target = webDataBinder.getTarget();
//        if (target == null) {
//            return;
//        }
//
//        System.out.println("Target=" + target);
//
//        if (target.getClass() == Account.class) {
//            webDataBinder.setValidator(accountValidator);
//        }
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "views/auth/login";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model) {
//        Account account = new Account();
//        model.addAttribute("account", account);
//        return "views/auth/register";
//    }
//
//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("account") Account accountRegistration,
//                           BindingResult result, Model model,final RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            redirectAttributes.addFlashAttribute("usernameErrors", result.getFieldErrors("username"));
//            redirectAttributes.addFlashAttribute("emailErrors", result.getFieldErrors("email"));
//            Account account= new Account();
//            account.setEmail(Objects.requireNonNull(result.getFieldValue("email")).toString());
//            account.setUsername(accountRegistration.getUsername());
////            redirectAttributes.addFlashAttribute("account", account);
//            model.addAttribute("account", account);
//            return "redirect:/register";
//        }
//
//        try {
//            accountService.register(accountRegistration);
//        } catch (Exception exception) {
//            redirectAttributes.addFlashAttribute("account", accountRegistration);
//            model.addAttribute("account", accountRegistration);
//            System.out.println("Exception: " + exception.getMessage());
//            return "redirect:/register?error";
//        }
//        redirectAttributes.addFlashAttribute("success", "Đăng ký tài khoản thành công!");
//        return "redirect:/register?success";
//    }
//
//
//    @PostMapping("/logout")
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }
//
//
//    @GetMapping("/403")
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            User loginUser = (User) ((Authentication) principal).getPrincipal();
//
//            String userInfo = WebUtils.toString(loginUser);
//
//            model.addAttribute("userInfo", userInfo);
//            System.out.println(userInfo);
//            String message = "Chào " + principal.getName() + "! Bạn không có quyền truy cập trang này!";
//            model.addAttribute("message", message);
//        }
//        return "views/403";
//    }
}
