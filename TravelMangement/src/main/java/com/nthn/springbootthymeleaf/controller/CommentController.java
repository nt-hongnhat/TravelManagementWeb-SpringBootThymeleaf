package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.CommentDTO;
import com.nthn.springbootthymeleaf.VO.CommentQueryVO;
import com.nthn.springbootthymeleaf.VO.CommentUpdateVO;
import com.nthn.springbootthymeleaf.VO.CommentVO;
import com.nthn.springbootthymeleaf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private
    CommentService commentService;

    @PostMapping
    public String save(@Valid @RequestBody CommentVO vO) {
        return commentService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        commentService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CommentUpdateVO vO) {
        commentService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CommentDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return commentService.getById(id);
    }

    @GetMapping
    public Page<CommentDTO> query(@Valid CommentQueryVO vO) {
        return commentService.query(vO);
    }
}
