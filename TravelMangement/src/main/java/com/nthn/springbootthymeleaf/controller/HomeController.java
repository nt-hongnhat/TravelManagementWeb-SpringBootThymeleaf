package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.TourGroup;
import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TourGroupService tourGroupService;
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private NewsService newsService;
    @Autowired
    private TourService tourService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PlacesService placesService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories(""));
        model.addAttribute("tourGroups", this.tourGroupService.getTourGroups(""));
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
        model.addAttribute("feedbacks", this.feedbackService.getFeedbacks(4.0));
        model.addAttribute("places", this.placesService.getPlaces());
        model.addAttribute("durations", this.tourService.getDurations());
    }


    @GetMapping("")
    public String index(Model model, Principal principal) {
        String profile = null;
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            profile = WebUtils.toString(user);
        }
        model.addAttribute("profile", profile);
        return "views/index";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "views/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "views/contact";
    }

    @GetMapping("/services")
    public String services(Model model) {
        return "views/services";
    }
    

}
