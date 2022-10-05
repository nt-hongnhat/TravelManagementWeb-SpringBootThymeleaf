package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.TourDTO;
import com.nthn.springbootthymeleaf.VO.TourQueryVO;
import com.nthn.springbootthymeleaf.VO.TourUpdateVO;
import com.nthn.springbootthymeleaf.VO.TourVO;
import com.nthn.springbootthymeleaf.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @PostMapping
    public String save(@Valid @RequestBody TourVO vO) {
        return tourService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        tourService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody TourUpdateVO vO) {
        tourService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TourDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return tourService.getById(id);
    }

    @GetMapping
    public Page<TourDTO> query(@Valid TourQueryVO vO) {
        return tourService.query(vO);
    }
}
