package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.*;
import com.nthn.springbootthymeleaf.service.*;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
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

    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SurchargeService surchargeService;
    @Autowired
    private PaypalService paypalService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("tourGroups", this.tourGroupService.getTourGroups());
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
        model.addAttribute("feedbacks", this.feedbackService.getFeedbacks(4.0));
        model.addAttribute("places", this.placesService.getPlaces());
        model.addAttribute("durations", this.tourService.getDurations());
    }

    @GetMapping("/booking/{id}")
    public String index(@PathVariable("id") Integer id, Model model) {
        Tour tour = tourService.getById(id);
        List<TourTicket> tourTickets = tour.getTourTickets();

        model.addAttribute("customer", new Customer());
        model.addAttribute("tour", tour);
        model.addAttribute("booking", new Booking());
        model.addAttribute("tourTickets", tourTickets);

        return "views/booking/booking";
    }

    @PostMapping("/booking/{id}")
    public String booking(@PathVariable("id") Integer id, @ModelAttribute("booking") Booking booking, @ModelAttribute("customer") Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
        Tour tour = tourService.getById(id);
        List<TourTicket> tourTickets = tour.getTourTickets();
        Customer existCustomer = customerService.getCustomer(customer.getIdentified());


        if (existCustomer == null) {
            existCustomer = customerService.save(customer);
        }

        booking.setTour(tour);
        booking.setCustomer(existCustomer);


        Booking savedBooking = bookingService.save(booking);
        System.out.println(booking);
        System.out.println(existCustomer);

        List<BookingDetail> bookingDetails = new ArrayList<>();


        tourTickets.forEach(tourTicket -> {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(savedBooking);
            int numberPeople;
            BigDecimal surcharge;
            switch (tourTicket.getName()) {
                case "Người lớn (Trên 11 tuổi)" -> {
                    bookingDetail.setTourTicket(tourTicket);
                    bookingDetail.setQuantity(booking.getNumberAdult());
                    if (tourTicket.getSurcharge() != null) {
                        surcharge = tourTicket.getSurcharge().getSurcharge();
                    } else {
                        surcharge = BigDecimal.valueOf(0);
                    }
                    bookingDetail.setUnitPrice(tourTicket.getUnitPrice().add(surcharge));
                    break;
                }
                case "Trẻ em (5 - 11 tuổi)" -> {
                    numberPeople = booking.getNumberChildren();
                    if (numberPeople > 0) {
                        bookingDetail.setTourTicket(tourTicket);
                        bookingDetail.setQuantity(numberPeople);
                        if (tourTicket.getSurcharge() != null) {
                            surcharge = tourTicket.getSurcharge().getSurcharge();
                        } else {
                            surcharge = BigDecimal.valueOf(0);
                        }
                        bookingDetail.setUnitPrice(tourTicket.getUnitPrice().add(surcharge));
                    }
                    break;
                }
                case "Trẻ nhỏ (2-5 tuổi)" -> {
                    numberPeople = booking.getNumberYoungChildren();
                    if (numberPeople > 0) {
                        bookingDetail.setTourTicket(tourTicket);
                        bookingDetail.setQuantity(numberPeople);
                        if (tourTicket.getSurcharge() != null) {
                            surcharge = tourTicket.getSurcharge().getSurcharge();
                        } else {
                            surcharge = BigDecimal.valueOf(0);
                        }
                        bookingDetail.setUnitPrice(tourTicket.getUnitPrice().add(surcharge));
                    }
                    break;
                }
                case "Sơ sinh (<2 tuổi)" -> {
                    numberPeople = booking.getNumberInfants();
                    if (numberPeople > 0) {
                        bookingDetail.setTourTicket(tourTicket);
                        bookingDetail.setQuantity(numberPeople);
                        if (tourTicket.getSurcharge() != null) {
                            surcharge = tourTicket.getSurcharge().getSurcharge();
                        } else {
                            surcharge = BigDecimal.valueOf(0);
                        }
                        bookingDetail.setUnitPrice(tourTicket.getUnitPrice().add(surcharge));
                    }
                    break;
                }
            }

            bookingDetailService.save(bookingDetail);

            System.out.println(bookingDetail);
        });

        redirectAttributes.addFlashAttribute("success", "Đặt tour thành công!");
        return "redirect:/booking/{id}?success";
    }


    @GetMapping("/bill/{id}")
    public String payment(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingService.getById(id);
        List<BookingDetail> bookingDetails = bookingDetailService.getBookingDetails(id);
        final BigDecimal[] total = {BigDecimal.valueOf(0)};
        bookingDetails.forEach(bookingDetail -> {
            bookingDetail.setTotal(bookingDetail.getUnitPrice().multiply(BigDecimal.valueOf(bookingDetail.getQuantity())));
            System.out.println(bookingDetail.getTotal());
            total[0] = total[0].add(bookingDetail.getTotal());
        });
        BigDecimal sum = BigDecimal.valueOf(0);
        for (BigDecimal bigDecimal : total) {
            sum = sum.add(bigDecimal);
        }

        booking.setTotal(sum);
        bookingService.update(booking.getId(), booking);

        model.addAttribute("bookingDetails", bookingDetails);
        model.addAttribute("booking", booking);

        return "views/booking/bill";
    }

    @PostMapping("/bill/{bookingId}/payment")
    public String payment(@PathVariable("bookingId") Integer bookingId, Model model, RedirectAttributes redirectAttributes) {
        Booking booking = bookingService.getById(bookingId);
        return "views/booking/bill";
    }


}
