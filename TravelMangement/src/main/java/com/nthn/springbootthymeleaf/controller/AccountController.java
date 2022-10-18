package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import com.nthn.springbootthymeleaf.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Validated
@Controller
@RequestMapping("/admin/accounts")
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
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("accounts", accountService.getAccounts(""));
        return "views/admin/accountManage";
    }

    // GET: /account/{id}
    @GetMapping("/{id}/details")
    public String details(@Validated @NotNull @PathVariable("id") Integer id, Model model) {
        model.addAttribute("account", accountService.getAccount(id));
        System.out.println(accountService.getAccount(id).getPhotosImagePath());
        return "views/admin/accountDetails";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.getAccount(id));

        return "views/admin/accountEdit";
    }

    // POST: /admin/accounts/{id}/edit
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model, @Validated @ModelAttribute("account") Account account, BindingResult result, final RedirectAttributes redirectAttributes) {
        Account account1 = accountService.getAccount(id);
        System.out.println(account);
        System.out.println(account1);

        if (result.hasErrors()) {
            model.addAttribute("account", account);
            model.addAttribute("permissions", permissionService.getPermissions());

            redirectAttributes.addFlashAttribute("errorMessage", result.getAllErrors().toString());
            return "redirect:/admin/accounts/{id}/edit?error";
        }
        try {
            accountService.update(id, account);
        } catch (Exception exception) {
            model.addAttribute("account", account);
            model.addAttribute("permissions", permissionService.getPermissions());
//            model.addAttribute("errorMessage", "Error" + exception.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error" + exception.getMessage());

            return "redirect:/admin/accounts/{id}/edit?error";
        }
        redirectAttributes.addFlashAttribute("success", "Saved account successfully!");
        return "redirect:/admin/accounts/{id}/edit?success";
    }

    // DELETE: /account/{id}
    @GetMapping("/{id}/delete")
    public String delete(@Valid @NotNull @PathVariable("id") Integer id) {
        accountService.delete(id);
        return "redirect:/admin/accounts/";
    }


    // Hiển thị trang tạo tài khoản
    // GET: /account/create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newAccount", new Account());
        model.addAttribute("permissions", permissionService.getPermissions());
        return "views/admin/accountCreate";
    }

    // POST: /account/save
    @PostMapping("/create")
    public String create(Model model, @RequestParam("image") MultipartFile multipartFile, @Validated @ModelAttribute("newAccount") Account newAccount, BindingResult result, final RedirectAttributes redirectAttributes) {
        model.addAttribute("newAccount", newAccount);

        if (!Objects.equals(newAccount.getConfirm(), newAccount.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu không khớp");
            return "redirect:/admin/accounts/create";
        }

        if (result.hasErrors()) {
            model.addAttribute("permissions", permissionService.getPermissions());
            redirectAttributes.addFlashAttribute("errorMessage", result.getAllErrors().toString());
            return "redirect:/admin/accounts/create";
        }
        Account account;
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            System.out.println(fileName);
            newAccount.setAvatarUrl(fileName);
            account = accountService.create(newAccount);


            String uploadDir = "avatar/" + account.getId();
            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);

            System.out.println(fileName);


        } catch (Exception exception) {
            model.addAttribute("permissions", permissionService.getPermissions());
            model.addAttribute("errorMessage", "Lỗi" + exception.getMessage());
            return "redirect:/admin/accounts/create";
        }

        redirectAttributes.addFlashAttribute("success", "Tạo tài khoản thành công!");
        redirectAttributes.addFlashAttribute("newAccount", account);
        return "redirect:/admin/accounts/create";
    }

}
