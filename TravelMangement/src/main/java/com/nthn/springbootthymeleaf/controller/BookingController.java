package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.*;
import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.service.impl.CategoryServiceImpl;
import com.nthn.springbootthymeleaf.service.PlacesService;
import com.nthn.springbootthymeleaf.service.impl.ProvinceServiceImpl;
import com.nthn.springbootthymeleaf.service.impl.TourGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private TourService tourService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private TourGroupServiceImpl tourGroupService;
    @Autowired
    private ProvinceServiceImpl provinceService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PlacesService placesService;

    @Autowired
    private BookingDetailService bookingDetailService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("tourGroups", this.tourGroupService.getTourGroups());
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

    @GetMapping("/{id}/bill")
    public String payment(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingService.getById(id);
        List<BookingDetail> bookingDetails = bookingDetailService.getBookingDetails(id);
        model.addAttribute("bookingDetails", bookingDetails);
        bookingDetails.forEach(bookingDetail -> System.out.println(bookingDetail.getQuantity()));
        System.out.println(booking.getBookingDate());

        return "views/bill";
    }

    @PostMapping("/{id}")
    public String booking(@PathVariable("id") Integer id, @ModelAttribute("booking") Booking booking, BindingResult result, RedirectAttributes redirectAttributes) {


        return "views/booking";
    }
}
