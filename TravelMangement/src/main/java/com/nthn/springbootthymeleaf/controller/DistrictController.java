package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.DistrictDTO;
import com.nthn.springbootthymeleaf.VO.DistrictQueryVO;
import com.nthn.springbootthymeleaf.VO.DistrictUpdateVO;
import com.nthn.springbootthymeleaf.VO.DistrictVO;
import com.nthn.springbootthymeleaf.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    public String save(@Valid @RequestBody DistrictVO vO) {
        return districtService.save(vO);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        districtService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DistrictUpdateVO vO) {
        districtService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DistrictDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return districtService.getById(id);
    }

    @GetMapping
    public Page<DistrictDTO> query(@Valid DistrictQueryVO vO) {
        return districtService.query(vO);
    }
}
