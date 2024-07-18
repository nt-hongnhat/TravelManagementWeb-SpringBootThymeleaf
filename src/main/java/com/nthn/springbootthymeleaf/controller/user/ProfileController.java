package com.nthn.springbootthymeleaf.controller.user;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.Customer;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.CloudinaryService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import com.nthn.springbootthymeleaf.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;

@Controller
@PreAuthorize("isAuthenticated()") //Phải đảm bảo là user login để có principal.id
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @ModelAttribute
    public void addAttributes(Model model, Principal principal) {
        Account account = new Account();
        if (principal != null) {
            account = accountService.getAccountByUsername(principal.getName());
        }
        model.addAttribute("currentUser", account);
    }
    
    @GetMapping("")
    public String profile(Model model, Principal principal) {
        Account account = new Account();
        if (principal != null) {
            account = accountService.getAccountByUsername(principal.getName());
        }
        
        System.out.println("account = " + account);
        System.out.println("customer = " + account.getCustomer());
        model.addAttribute("account", account);
        model.addAttribute("customer", account.getCustomer());
        return "views/profile";
    }
    
    @PostMapping("/update-account/{accountId}")
    public String profile(@ModelAttribute("account") Account account, Model model,
                          @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes,
                          @PathVariable int accountId) {
        if (account != null) {
            System.out.println("account = " + account);
            if (multipartFile != null) {
                System.out.println("multipartFile = " + multipartFile);
                Map map = cloudinaryService.upload(multipartFile);
                account.setAvatarUrl(map.get("url").toString());
            }
            account = accountService.update(accountId, account);
            redirectAttributes.addFlashAttribute("successMessage", "Tài khoản đã được cập nhật");
            redirectAttributes.addFlashAttribute("account", account);
            model.addAttribute("account", account);
            return "redirect:/profile?success";
        }
        return "redirect:/profile?error";
    }
    
    @PostMapping("/update-customer/{customerId}")
    public String profile(@PathVariable("customerId") int customerId, @ModelAttribute("customer") Customer customer,
                          Model model, RedirectAttributes redirectAttributes) {
        customer = customerService.update(customerId, customer);
        redirectAttributes.addFlashAttribute("customer", customer);
        redirectAttributes.addFlashAttribute("successMessage", "Tài khoản đã được cập nhật thành công!");
        return "redirect:/profile?success";
    }
    
    
}
