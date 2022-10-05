package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.BookingDTO;
import com.nthn.springbootthymeleaf.VO.BookingQueryVO;
import com.nthn.springbootthymeleaf.VO.BookingUpdateVO;
import com.nthn.springbootthymeleaf.VO.BookingVO;
import com.nthn.springbootthymeleaf.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String save(@Valid @RequestBody BookingVO vO) {
        return bookingService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        bookingService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody BookingUpdateVO vO) {
        bookingService.update(id, vO);
    }

    @GetMapping("/{id}")
    public BookingDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return bookingService.getById(id);
    }

    @GetMapping
    public Page<BookingDTO> query(@Valid BookingQueryVO vO) {
        return bookingService.query(vO);
    }
}
