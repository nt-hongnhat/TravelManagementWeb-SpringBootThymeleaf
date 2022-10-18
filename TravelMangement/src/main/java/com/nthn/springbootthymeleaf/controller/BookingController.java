package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.*;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private TourService tourService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TourGroupService tourGroupService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private FeedbackService feedbackService;
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

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Integer id, Model model) {
        Tour tour = tourService.getById(id);
        Surcharge surcharge;
        Booking booking = new Booking();

        Customer customer = new Customer();

        model.addAttribute("customer", customer);
        model.addAttribute("tour", tour);
        model.addAttribute("booking", booking);

        return "views/booking";
    }

    @PostMapping("/{id}")
    public String booking(@PathVariable("id") Integer id, @ModelAttribute("booking") Booking booking, BindingResult result, RedirectAttributes redirectAttributes) {


        return "views/booking";
    }
}
