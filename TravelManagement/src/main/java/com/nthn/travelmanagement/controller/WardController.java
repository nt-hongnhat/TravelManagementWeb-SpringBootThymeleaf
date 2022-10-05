package com.nthn.travelmanagement.controller;

import com.nthn.travelmanagement.DTO.WardQueryVO;
import com.nthn.travelmanagement.DTO.WardUpdateVO;
import com.nthn.travelmanagement.DTO.WardVO;
import com.nthn.travelmanagement.repository.Ward;
import com.nthn.travelmanagement.service.WardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "")
@Validated
@RestController
@RequestMapping("/ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @PostMapping
    @ApiOperation("Save ")
    public String save(@Valid @RequestBody WardVO vO) {
        return wardService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete ")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        wardService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update ")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody WardUpdateVO vO) {
        wardService.update(id, vO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieve by ID ")
    public Ward getById(@Valid @NotNull @PathVariable("id") String id) {
        return wardService.getById(id);
    }

    @GetMapping
    @ApiOperation("Retrieve by query ")
    public Page<Ward> query(@Valid WardQueryVO vO) {
        return wardService.query(vO);
    }
}
