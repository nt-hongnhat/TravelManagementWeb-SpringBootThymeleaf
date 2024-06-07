package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.model.Account;
import com.nthn.springbootthymeleaf.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Objects;

@Validated
@Controller
@RequestMapping("/dashboard/accounts")
public class AccountController {
//    @Autowired
//    private AccountService accountService;
//    @Autowired
//    private PermissionService permissionService;
////
////    @Autowired
////    private Cloudinary cloudinary;
//
//    @ModelAttribute
//    public void commonAttribute(Model model, HttpSession httpSession) {
//        User currentUser = (User) httpSession.getAttribute("currentUser");
//        Account account = accountService.getAccountByUsername(currentUser.getUsername());
//        model.addAttribute("permissions", permissionService.getPermissions());
//        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
//        model.addAttribute("avatar", account.getPhotosImagePath());
//    }
//
//    // GET: /accounts
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("accounts", accountService.getAccounts(""));
//        return "views/admin/account/list";
//    }
//
//    // GET: /accounts/{id}
//    @GetMapping("/{id}/edit")
//    public String details(@Validated @NotNull @PathVariable("id") Integer id, Model model) {
//        model.addAttribute("account", accountService.getAccount(id));
//        System.out.println(accountService.getAccount(id).getPhotosImagePath());
//        return "views/admin/account/details";
//    }
//
//
//    // POST: /dashboard/accounts/{id}/edit
//    @PostMapping("/{id}/edit")
//    public String save(@PathVariable Integer id, @RequestParam("image") MultipartFile multipartFile, Model model, @Validated @ModelAttribute("account") Account account, BindingResult result, final RedirectAttributes redirectAttributes) {
//        System.out.println(account.getAvatarUrl());
//        System.out.println(account.getActive());
////
////        if (result.hasErrors()) {
////            model.addAttribute("account", account);
////            model.addAttribute("permissions", permissionService.getPermissions());
////
////            redirectAttributes.addFlashAttribute("errorMessage", result.getAllErrors().toString());
////            return "redirect:/dashboard/accounts/{id}/edit?error";
////        }
//        try {
//            if (!multipartFile.isEmpty()) {
//                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//                account.setAvatarUrl(fileName);
//
//                String uploadDir = "src/main/resources/static/avatar/" + account.getId();
//                FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
//            }
//
//            accountService.update(id, account);
//
//        } catch (Exception exception) {
//            model.addAttribute("account", account);
//            model.addAttribute("permissions", permissionService.getPermissions());
////            model.addAttribute("errorMessage", "Error" + exception.getMessage());
//            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi!!! Thử lại!");
//
//            return "redirect:/dashboard/accounts/{id}/edit?error";
//        }
//
//        redirectAttributes.addFlashAttribute("success", "Lưu thay đổi thành công!");
//        return "redirect:/dashboard/accounts/{id}/edit?success";
//    }
//
//    // DELETE: /account/{id}
//    @GetMapping("/{id}/delete")
//    public String delete(@Valid @NotNull @PathVariable("id") Integer id) {
//        accountService.delete(id);
//        return "redirect:/dashboard/accounts/";
//    }
//
//
//    // Hiển thị trang tạo tài khoản
//    // GET: /account/create
//    @GetMapping("/create")
//    public String create(Model model) {
//        model.addAttribute("newAccount", new Account());
//        model.addAttribute("permissions", permissionService.getPermissions());
//        return "views/admin/account/create";
//    }
//
//
//    // POST: /account/save
//    @PostMapping("/create")
//    public String create(@RequestParam("image") MultipartFile multipartFile, @Validated @ModelAttribute("newAccount") Account newAccount,
//                         Model model, BindingResult result,
//                         final RedirectAttributes redirectAttributes) throws IOException {
//        model.addAttribute("newAccount", newAccount);
//
//
//        if (result.hasErrors()) {
//            model.addAttribute("permissions", permissionService.getPermissions());
//            redirectAttributes.addFlashAttribute("errorMessage", result.getAllErrors().toString());
//            return "redirect:/dashboard/accounts/create?error";
//        }
//
//        Account account;
//
//        try {
//            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//
//            newAccount.setAvatarUrl(fileName);
//            account = accountService.create(newAccount);
//
//            String uploadDir = "src/main/resources/static/avatar/" + account.getId();
//            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
//
//        } catch (Exception exception) {
//            model.addAttribute("permissions", permissionService.getPermissions());
//            model.addAttribute("errorMessage", "Lỗi" + exception.getMessage());
//            return "redirect:/dashboard/accounts/create?error";
//        }
//
//        redirectAttributes.addFlashAttribute("success", "Tạo tài khoản thành công!");
//        redirectAttributes.addFlashAttribute("newAccount", account);
//        return "redirect:/dashboard/accounts/create?success";
//    }

}
