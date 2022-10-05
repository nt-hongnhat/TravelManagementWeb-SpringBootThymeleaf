package com.nthn.travelmanagement.controller;

import com.nthn.travelmanagement.DTO.ProvinceQueryVO;
import com.nthn.travelmanagement.DTO.ProvinceUpdateVO;
import com.nthn.travelmanagement.DTO.ProvinceVO;
import com.nthn.travelmanagement.repository.Province;
import com.nthn.travelmanagement.service.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "")
@Validated
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    @ApiOperation("Save ")
    public String save(@Valid @RequestBody ProvinceVO vO) {
        return provinceService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete ")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        provinceService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update ")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ProvinceUpdateVO vO) {
        provinceService.update(id, vO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieve by ID ")
    public Province getById(@Valid @NotNull @PathVariable("id") String id) {
        return provinceService.getById(id);
    }

    @GetMapping
    @ApiOperation("Retrieve by query ")
    public Page<Province> query(@Valid ProvinceQueryVO vO) {
        return provinceService.query(vO);
    }
}
