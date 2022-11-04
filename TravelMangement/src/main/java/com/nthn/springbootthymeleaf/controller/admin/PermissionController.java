package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.pojo.Permission;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private AccountService accountService;

    @ModelAttribute
    public void commonAttribute(Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        Account account = accountService.getAccountByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
        model.addAttribute("avatar", account.getPhotosImagePath());
    }

    @GetMapping
    public String index(Model model) {
        List<Permission> permissions = permissionService.getPermissions();
        Map<String, Integer> chartData = new LinkedHashMap<>();

        permissions.forEach(permission -> {
            chartData.put(permission.getName(), permission.getCountAccount());
        });

        model.addAttribute("permissions", permissions);
        model.addAttribute("chartData", chartData);

        return "views/admin/permission/list";
    }


}
