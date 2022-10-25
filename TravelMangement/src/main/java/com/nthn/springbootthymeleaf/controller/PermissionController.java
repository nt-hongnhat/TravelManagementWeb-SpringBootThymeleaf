package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Permission;
import com.nthn.springbootthymeleaf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

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
