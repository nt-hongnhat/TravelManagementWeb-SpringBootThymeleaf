package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.TransportDTO;
import com.nthn.springbootthymeleaf.VO.TransportQueryVO;
import com.nthn.springbootthymeleaf.VO.TransportUpdateVO;
import com.nthn.springbootthymeleaf.VO.TransportVO;
import com.nthn.springbootthymeleaf.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @PostMapping
    public String save(@Valid @RequestBody TransportVO vO) {
        return transportService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        transportService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody TransportUpdateVO vO) {
        transportService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TransportDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return transportService.getById(id);
    }

    @GetMapping
    public Page<TransportDTO> query(@Valid TransportQueryVO vO) {
        return transportService.query(vO);
    }
}
