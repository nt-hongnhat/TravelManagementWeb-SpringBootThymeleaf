package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.ProvinceDTO;
import com.nthn.springbootthymeleaf.VO.ProvinceQueryVO;
import com.nthn.springbootthymeleaf.VO.ProvinceUpdateVO;
import com.nthn.springbootthymeleaf.VO.ProvinceVO;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    public String save(@Valid @RequestBody ProvinceVO vO) {
        return provinceService.save(vO);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        provinceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ProvinceUpdateVO vO) {
        provinceService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ProvinceDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return provinceService.getById(id);
    }

    @GetMapping
    public Page<ProvinceDTO> query(@Valid ProvinceQueryVO vO) {
        return provinceService.query(vO);
    }
}
