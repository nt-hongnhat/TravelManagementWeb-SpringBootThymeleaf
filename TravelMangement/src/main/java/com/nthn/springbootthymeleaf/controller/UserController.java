package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.EmployeeDTO;
import com.nthn.springbootthymeleaf.DTO.UserDTO;
import com.nthn.springbootthymeleaf.VO.UserQueryVO;
import com.nthn.springbootthymeleaf.VO.UserUpdateVO;
import com.nthn.springbootthymeleaf.VO.UserVO;
import com.nthn.springbootthymeleaf.model.User;
import com.nthn.springbootthymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/userList";
    }

    @PostMapping("/save")
    public String save(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "admin/userCreate";
        userService.save(userDTO);
        redirectAttributes.addFlashAttribute("success", "Saved account successfully!");
        return "redirect:/admin/user";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("account", new User());
        return "admin/userCreate";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        UserDTO userDTO = userService.getById(id);
        userService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Deleted account successfully");
        return "redirect:/admin/user";
    }

    @PutMapping("/{id}/edit")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("account", userService.getById(id));
        return "admin/userCreate";
    }

}
