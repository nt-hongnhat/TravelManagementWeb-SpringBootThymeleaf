package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.WorkForDTO;
import com.nthn.springbootthymeleaf.VO.WorkForQueryVO;
import com.nthn.springbootthymeleaf.VO.WorkForUpdateVO;
import com.nthn.springbootthymeleaf.VO.WorkForVO;
import com.nthn.springbootthymeleaf.service.WorkForService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/workFor")
public class WorkForController {

    @Autowired
    private WorkForService workForService;

    @PostMapping
    public String save(@Valid @RequestBody WorkForVO vO) {
        return workForService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        workForService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody WorkForUpdateVO vO) {
        workForService.update(id, vO);
    }

    @GetMapping("/{id}")
    public WorkForDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return workForService.getById(id);
    }

    @GetMapping
    public Page<WorkForDTO> query(@Valid WorkForQueryVO vO) {
        return workForService.query(vO);
    }
}
