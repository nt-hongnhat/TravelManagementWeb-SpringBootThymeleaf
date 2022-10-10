package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Comment;
import com.nthn.springbootthymeleaf.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    // Hiển thị trang tin tức
    // GET: /news
    @GetMapping
    public String index(Model model) {
        model.addAttribute("news", newsService.getNews(""));
        return "views/newsList";
    }

    // Xem chi tiết tin tức
    // GET: /news/{id}
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("news", newsService.getById(id));
        return "views/newsDetails";
    }

    // POST: /news/{id}
    @PostMapping("/{id}")
    public String comment(@PathVariable("id") Integer id, Comment comment) {
        return null;
    }
}
