package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.CustomerDTO;
import com.nthn.springbootthymeleaf.VO.CustomerQueryVO;
import com.nthn.springbootthymeleaf.VO.CustomerUpdateVO;
import com.nthn.springbootthymeleaf.VO.CustomerVO;
import com.nthn.springbootthymeleaf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public String save(@Valid @RequestBody CustomerVO vO) {
        return customerService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        customerService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CustomerUpdateVO vO) {
        customerService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return customerService.getById(id);
    }

    @GetMapping
    public Page<CustomerDTO> query(@Valid CustomerQueryVO vO) {
        return customerService.query(vO);
    }
}
