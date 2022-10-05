package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.TourPriceDTO;
import com.nthn.springbootthymeleaf.VO.TourPriceQueryVO;
import com.nthn.springbootthymeleaf.VO.TourPriceUpdateVO;
import com.nthn.springbootthymeleaf.VO.TourPriceVO;
import com.nthn.springbootthymeleaf.service.TourPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/tourPrice")
public class TourPriceController {

    @Autowired
    private TourPriceService tourPriceService;

    @PostMapping
    public String save(@Valid @RequestBody TourPriceVO vO) {
        return tourPriceService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        tourPriceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody TourPriceUpdateVO vO) {
        tourPriceService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TourPriceDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return tourPriceService.getById(id);
    }

    @GetMapping
    public Page<TourPriceDTO> query(@Valid TourPriceQueryVO vO) {
        return tourPriceService.query(vO);
    }
}
