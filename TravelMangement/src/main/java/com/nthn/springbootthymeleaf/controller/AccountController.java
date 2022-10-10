package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.AccountDTO;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;

    @ModelAttribute
    public void commonAttribute(Model model) {
        model.addAttribute("permissions", permissionService.getPermissions());
    }

    // GET: /account
    @GetMapping
    public String index(Model model) {
        model.addAttribute("accounts", accountService.getAccounts(""));
        return "views/admin/accountList";
    }

    // GET: /account/{id}
    @GetMapping("/{id}")
    public String details(@Validated @NotNull @PathVariable("id") Integer id, Model model) {
        model.addAttribute("account", accountService.getAccount(id));
        return "views/admin/accountDetails";
    }

    @PutMapping("/{id}/edit")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.getAccount(id));
        return "views/admin/accountEdit";
    }

    // DELETE: /account/{id}
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        accountService.delete(id);
    }

    // Hiển thị trang tạo tài khoản
    // GET: /account/create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("permissions", permissionService.getPermissions());
        return "views/admin/accountCreate";
    }

    // POST: /account/save
    @PostMapping("/save")
    public String save(Model model, @Validated AccountDTO account, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("permissions", permissionService.getPermissions());
            return "views/admin/accountCreate";
        }
        try {
            accountService.create(account);
        } catch (Exception exception) {
            model.addAttribute("permissions", permissionService.getPermissions());
            model.addAttribute("errorMessage", "Error" + exception.getMessage());
            return "views/admin/accountCreate";
        }
        redirectAttributes.addFlashAttribute("success", "Saved account successfully!");
        return "redirect:/";
    }

}
