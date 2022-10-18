package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
import com.nthn.springbootthymeleaf.pojo.TourSchedule;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
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
    @Autowired
    private TourGroupService tourGroupService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private NewsService newsService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private TourScheduleService tourScheduleService;
    @Autowired
    private PlacesService placesService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("tourGroups", this.tourGroupService.getTourGroups(""));
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
        model.addAttribute("feedbacks", this.feedbackService.getFeedbacks(4.0));
        model.addAttribute("places", this.placesService.getPlaces());
        model.addAttribute("durations", this.tourService.getDurations());
    }

    @GetMapping("/{linkStatic}")
    public String getToursByTourGroup(@PathVariable("linkStatic") String linkStatic, Model model) {
        TourGroup tourGroup = tourGroupService.getTourGroup(linkStatic);
        Set<Tour> tours = tourGroup.getTours();
        model.addAttribute("tourGroup", tourGroup);
        model.addAttribute("tours", tours);
        return "views/tour";
    }

    @GetMapping("/tour/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Tour tour = tourService.getById(id);
        Set<TourSchedule> tourSchedules = tour.getTourSchedules();

        model.addAttribute("tour", tour);
        model.addAttribute("tourSchedules", tourSchedules);

        return "views/tourDetails";
    }

    @GetMapping("/tours")
    public String searchTours(@RequestParam(required = false) Map<String, String> params, Model model) {
        String departure = params.getOrDefault("departure", "");

        return "views/tourList";
    }
}
