package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.DestinationDTO;
import com.nthn.springbootthymeleaf.VO.DestinationQueryVO;
import com.nthn.springbootthymeleaf.VO.DestinationUpdateVO;
import com.nthn.springbootthymeleaf.VO.DestinationVO;
import com.nthn.springbootthymeleaf.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping
    public String save(@Valid @RequestBody DestinationVO vO) {
        return destinationService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        destinationService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody DestinationUpdateVO vO) {
        destinationService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DestinationDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return destinationService.getById(id);
    }

    @GetMapping
    public Page<DestinationDTO> query(@Valid DestinationQueryVO vO) {
        return destinationService.query(vO);
    }
}
