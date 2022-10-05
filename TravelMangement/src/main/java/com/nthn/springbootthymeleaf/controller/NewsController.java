package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.NewsDTO;
import com.nthn.springbootthymeleaf.VO.NewsQueryVO;
import com.nthn.springbootthymeleaf.VO.NewsUpdateVO;
import com.nthn.springbootthymeleaf.VO.NewsVO;
import com.nthn.springbootthymeleaf.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public String index(Model model) {
        return "views/news";
    }

    @PostMapping
    public String save(@Valid @RequestBody NewsVO vO) {
        return newsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        newsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody NewsUpdateVO vO) {
        newsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public NewsDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return newsService.getById(id);
    }
//
//    @GetMapping
//    public Page<NewsDTO> query(@Valid NewsQueryVO vO) {
//        return newsService.query(vO);
//    }
}
