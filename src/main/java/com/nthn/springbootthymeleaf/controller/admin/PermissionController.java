package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.entity.Permission;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/permissions")
@RequiredArgsConstructor
public class PermissionController {

  private final AccountService accountService;
  private final PermissionService permissionService;

  @ModelAttribute
  public void commonAttribute(@NotNull Model model, @NotNull HttpSession httpSession) {
    model.addAttribute(
        AttributeName.CURRENT_USER,
        accountService.getAccountByUsername(
            ((User) httpSession.getAttribute(AttributeName.CURRENT_USER)).getUsername()));
  }

  @GetMapping(EndpointConstants.PATH_VARIABLE_ID)
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute(AttributeName.PERMISSION, permissionService.getPermissionById(id));
    return "views/admin/permission/details";
  }

  @GetMapping(value = {"", "/"})
  public String index(Model model) {
    final List<Permission> permissions = permissionService.getAllPermission();

    model.addAttribute(AttributeName.PERMISSIONS, permissions);
    model.addAttribute(
        AttributeName.CHART_DATA,
        permissions.stream()
            .collect(
                Collectors.groupingBy(
                    Permission::getName, Collectors.summingInt(Permission::getCountAccount))));

    return "views/admin/permission/list";
  }

  @PostMapping("/save")
  public String save(
      @ModelAttribute("permission") Permission permission, RedirectAttributes redirectAttributes) {
    if (Objects.isNull(permission.getId())) {
      permission = permissionService.addPermission(permission);
      redirectAttributes.addFlashAttribute(
          AttributeName.SUCCESS_MESSAGE, "Phân quyền đã được cập nhật thành công!");

      return "redirect:/dashboard/permissions/" + permission.getId();
    }

    permission = permissionService.updatePermission(permission.getId(), permission);
    redirectAttributes.addFlashAttribute(
        AttributeName.SUCCESS_MESSAGE, "Phân quyền đã được cập nhật thành công!");

    return "redirect:/dashboard/permissions/" + permission.getId() + "?success";
  }
}
