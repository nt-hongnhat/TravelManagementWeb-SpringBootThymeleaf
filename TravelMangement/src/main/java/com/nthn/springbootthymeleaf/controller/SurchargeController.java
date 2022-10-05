package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.SurchargeDTO;
import com.nthn.springbootthymeleaf.VO.SurchargeQueryVO;
import com.nthn.springbootthymeleaf.VO.SurchargeUpdateVO;
import com.nthn.springbootthymeleaf.VO.SurchargeVO;
import com.nthn.springbootthymeleaf.service.SurchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/surcharge")
public class SurchargeController {

    @Autowired
    private SurchargeService surchargeService;

    @PostMapping
    public String save(@Valid @RequestBody SurchargeVO vO) {
        return surchargeService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        surchargeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody SurchargeUpdateVO vO) {
        surchargeService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SurchargeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return surchargeService.getById(id);
    }

    @GetMapping
    public Page<SurchargeDTO> query(@Valid SurchargeQueryVO vO) {
        return surchargeService.query(vO);
    }
}
