package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.BookingDetailDTO;
import com.nthn.springbootthymeleaf.VO.BookingDetailQueryVO;
import com.nthn.springbootthymeleaf.VO.BookingDetailUpdateVO;
import com.nthn.springbootthymeleaf.VO.BookingDetailVO;
import com.nthn.springbootthymeleaf.service.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/bookingDetail")
public class BookingDetailController {

    @Autowired
    private BookingDetailService bookingDetailService;

    @PostMapping
    public String save(@Valid @RequestBody BookingDetailVO vO) {
        return bookingDetailService.save(vO);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        bookingDetailService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody BookingDetailUpdateVO vO) {
        bookingDetailService.update(id, vO);
    }

    @GetMapping("/{id}")
    public BookingDetailDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return bookingDetailService.getById(id);
    }

    @GetMapping
    public Page<BookingDetailDTO> query(@Valid BookingDetailQueryVO vO) {
        return bookingDetailService.query(vO);
    }
}
