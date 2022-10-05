package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.TimetableDTO;
import com.nthn.springbootthymeleaf.VO.TimetableQueryVO;
import com.nthn.springbootthymeleaf.VO.TimetableUpdateVO;
import com.nthn.springbootthymeleaf.VO.TimetableVO;
import com.nthn.springbootthymeleaf.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public String save(@Valid @RequestBody TimetableVO vO) {
        return timetableService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        timetableService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody TimetableUpdateVO vO) {
        timetableService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TimetableDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return timetableService.getById(id);
    }

    @GetMapping
    public Page<TimetableDTO> query(@Valid TimetableQueryVO vO) {
        return timetableService.query(vO);
    }
}
