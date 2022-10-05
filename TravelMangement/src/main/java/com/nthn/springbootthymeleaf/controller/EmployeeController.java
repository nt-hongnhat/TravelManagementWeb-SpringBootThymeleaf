package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.EmployeeDTO;
import com.nthn.springbootthymeleaf.VO.EmployeeQueryVO;
import com.nthn.springbootthymeleaf.VO.EmployeeUpdateVO;
import com.nthn.springbootthymeleaf.VO.EmployeeVO;
import com.nthn.springbootthymeleaf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public String save(@Valid @RequestBody EmployeeVO vO) {
        return employeeService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody EmployeeUpdateVO vO) {
        employeeService.update(id, vO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public Page<EmployeeDTO> query(@Valid EmployeeQueryVO vO) {
        return employeeService.query(vO);
    }
}
