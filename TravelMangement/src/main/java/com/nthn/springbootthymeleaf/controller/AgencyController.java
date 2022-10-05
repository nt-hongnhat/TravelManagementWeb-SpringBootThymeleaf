package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.AgencyDTO;
import com.nthn.springbootthymeleaf.VO.AgencyQueryVO;
import com.nthn.springbootthymeleaf.VO.AgencyUpdateVO;
import com.nthn.springbootthymeleaf.VO.AgencyVO;
import com.nthn.springbootthymeleaf.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @PostMapping
    public String save(@Valid @RequestBody AgencyVO vO) {
        return agencyService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        agencyService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AgencyUpdateVO vO) {
        agencyService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AgencyDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return agencyService.getById(id);
    }

    @GetMapping
    public Page<AgencyDTO> query(@Valid AgencyQueryVO vO) {
        return agencyService.query(vO);
    }
}
