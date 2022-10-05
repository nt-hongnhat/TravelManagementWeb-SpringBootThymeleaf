package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.TransferDTO;
import com.nthn.springbootthymeleaf.VO.TransferQueryVO;
import com.nthn.springbootthymeleaf.VO.TransferUpdateVO;
import com.nthn.springbootthymeleaf.VO.TransferVO;
import com.nthn.springbootthymeleaf.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public String save(@Valid @RequestBody TransferVO vO) {
        return transferService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        transferService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody TransferUpdateVO vO) {
        transferService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TransferDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return transferService.getById(id);
    }

    @GetMapping
    public Page<TransferDTO> query(@Valid TransferQueryVO vO) {
        return transferService.query(vO);
    }
}
