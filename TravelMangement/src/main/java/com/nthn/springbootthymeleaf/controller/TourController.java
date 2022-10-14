package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Category;
import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/{locale:en|vi}/tours")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private SurchargeService surchargeService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tours", tourService.getTours());
        return "views/tour";
    }

    @GetMapping("/{categoryId}")
    public String tours(@PathVariable("categoryId") String categoryId, @RequestParam(required = false) Map<String, String> params, Model model) {

        Category category = categoryService.getCategoryById(Integer.valueOf(categoryId));
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        int size = Integer.parseInt(params.getOrDefault("size", "10"));
        Pageable pageable = PageRequest.of(page, size);
        Page<Tour> tourPage = tourService.getTours(Integer.valueOf(categoryId), pageable);
        model.addAttribute("tours", tourPage.getContent());
        model.addAttribute("provinces", provinceService.getProvinces(""));
        return "views/tour";
    }
}
