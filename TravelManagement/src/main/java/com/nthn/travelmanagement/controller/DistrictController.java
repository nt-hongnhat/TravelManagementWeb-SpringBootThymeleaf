package com.nthn.travelmanagement.controller;

import com.nthn.travelmanagement.DTO.DistrictQueryVO;
import com.nthn.travelmanagement.DTO.DistrictUpdateVO;
import com.nthn.travelmanagement.DTO.DistrictVO;
import com.nthn.travelmanagement.repository.District;
import com.nthn.travelmanagement.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "")
@Validated
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    @ApiOperation("Save ")
    public String save(@Valid @RequestBody DistrictVO vO) {
        return districtService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete ")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        districtService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update ")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DistrictUpdateVO vO) {
        districtService.update(id, vO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieve by ID ")
    public District getById(@Valid @NotNull @PathVariable("id") String id) {
        return districtService.getById(id);
    }

    @GetMapping
    @ApiOperation("Retrieve by query ")
    public Page<District> query(@Valid DistrictQueryVO vO) {
        return districtService.query(vO);
    }
}
