package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.UserRoleDTO;
import com.nthn.springbootthymeleaf.VO.UserRoleQueryVO;
import com.nthn.springbootthymeleaf.VO.UserRoleUpdateVO;
import com.nthn.springbootthymeleaf.VO.UserRoleVO;
import com.nthn.springbootthymeleaf.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public String save(@Valid @RequestBody UserRoleVO vO) {
        return userRoleService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userRoleService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserRoleUpdateVO vO) {
        userRoleService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserRoleDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userRoleService.getById(id);
    }

    @GetMapping
    public Page<UserRoleDTO> query(@Valid UserRoleQueryVO vO) {
        return userRoleService.query(vO);
    }
}
