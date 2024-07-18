package com.nthn.springbootthymeleaf.controller.guest;

import com.nthn.springbootthymeleaf.entity.*;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TourGroupService tourGroupService;
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private TourService tourService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PlacesService placesService;

    @ModelAttribute
    public void commonAttributes(Model model, HttpSession httpSession) {
        LocalDate now = LocalDate.now();
        List<Category> categories = categoryService.getCategories();
        List<TourGroup> tourGroups = tourGroupService.getTourGroups();
        List<Province> provinces = provinceService.getProvinces();
        List<Places> places = placesService.getPlaces();
        List<Feedback> feedbacks = feedbackService.getFeedbacks(4.0);


        model.addAttribute("now", now);
        model.addAttribute("categories", categories);
        model.addAttribute("tourGroups", tourGroups);
        model.addAttribute("provinces", provinces);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("places", places);
        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
    }


}
