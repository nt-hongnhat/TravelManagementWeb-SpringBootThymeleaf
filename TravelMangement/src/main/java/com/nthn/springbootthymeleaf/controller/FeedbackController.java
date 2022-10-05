package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.FeedbackDTO;
import com.nthn.springbootthymeleaf.VO.FeedbackQueryVO;
import com.nthn.springbootthymeleaf.VO.FeedbackUpdateVO;
import com.nthn.springbootthymeleaf.VO.FeedbackVO;
import com.nthn.springbootthymeleaf.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public String save(@Valid @RequestBody FeedbackVO vO) {
        return feedbackService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        feedbackService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody FeedbackUpdateVO vO) {
        feedbackService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FeedbackDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return feedbackService.getById(id);
    }

    @GetMapping
    public Page<FeedbackDTO> query(@Valid FeedbackQueryVO vO) {
        return feedbackService.query(vO);
    }
}
