package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.WardDTO;
import com.nthn.springbootthymeleaf.VO.WardQueryVO;
import com.nthn.springbootthymeleaf.VO.WardUpdateVO;
import com.nthn.springbootthymeleaf.VO.WardVO;
import com.nthn.springbootthymeleaf.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @PostMapping
    public Integer save(@Valid @RequestBody WardVO vO) {
        return wardService.save(vO);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        wardService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody WardUpdateVO vO) {
        wardService.update(id, vO);
    }

    @GetMapping("/{id}")
    public WardDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return wardService.getById(id);
    }

    @GetMapping
    public Page<WardDTO> query(@Valid WardQueryVO vO) {
        return wardService.query(vO);
    }
}
