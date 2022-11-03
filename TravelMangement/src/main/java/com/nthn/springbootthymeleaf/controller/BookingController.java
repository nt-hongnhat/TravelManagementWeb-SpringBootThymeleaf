package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.BookingTourDTO;
import com.nthn.springbootthymeleaf.DTO.SearchHistoryDTO;
import com.nthn.springbootthymeleaf.pojo.*;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
//@Transactional
@RequestMapping("/booking")
@PreAuthorize("isAuthenticated()") //Phải đảm bảo là user login để có principal.id
//@PreAuthorize("hasAnyRole('CUSTOMER', 'EMPLOYEE', 'ADMIN')")
//@PreAuthorize("isAuthenticated()")
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
    @Autowired
    private TourTicketService tourTicketService;
    @Autowired
    private AccountService accountService;

    @ModelAttribute
    public void commonAttributes(Model model, Principal principal) {
        model.addAttribute("tourGroups", this.tourGroupService.getTourGroups());
        model.addAttribute("provinces", this.provinceService.getProvinces(""));
        model.addAttribute("feedbacks", this.feedbackService.getFeedbacks(4.0));
        model.addAttribute("places", this.placesService.getPlaces());
        model.addAttribute("durations", this.tourService.getDurations());
        Account account = new Account();
        if (principal != null) {
            account = accountService.getAccountByUsername(principal.getName());
        }

        model.addAttribute("currentUser", account);
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Integer id, Model model) {
        Tour tour = tourService.getById(id);
        List<TourTicket> tourTickets = tourTicketService.getTourTicketsByTour(id);

        BookingTourDTO bookingTourDTO = new BookingTourDTO();

        model.addAttribute("tour", tour);
        model.addAttribute("booking", bookingTourDTO);
        model.addAttribute("tourTickets", tourTickets);
        model.addAttribute("maxSlot", tour.getAvailableSlot());

        return "views/booking/booking";
    }


    @PostMapping("/{id}")
    public String booking(@PathVariable("id") Integer id, @ModelAttribute("booking") BookingTourDTO bookingTourDTO, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
        bookingTourDTO.setTourId(id);
        System.out.println(bookingTourDTO);
        Tour tour = tourService.getById(id);
        if (result.hasErrors()) {
            model.addAttribute("booking", bookingTourDTO);

            redirectAttributes.addFlashAttribute("errorMessage", result.getAllErrors().toString());
            return "redirect:/booking/{id}?error";
        }

        Booking saveBooking = new Booking();

        try {
            saveBooking = bookingService.add(bookingTourDTO);
            System.out.println(saveBooking);
        } catch (Exception exception) {
            model.addAttribute("booking", bookingTourDTO);
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi! Thử lại!!");
            System.out.println("ERROR -----" + exception.getMessage());
            return "redirect:/booking/{id}?error";
        }

        redirectAttributes.addFlashAttribute("success", "Đặt tour thành công!");
        return "redirect:/booking/bill/" + saveBooking.getId();
    }


    @GetMapping("/bill/{id}")
    public String payment(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingService.getById(id);
        List<BookingDetail> bookingDetails = bookingDetailService.getBookingDetails(id);
        final BigDecimal[] total = {BigDecimal.valueOf(0)};
        bookingDetails.forEach(bookingDetail -> {
            Surcharge surcharge = bookingDetail.getTourTicket().getSurcharge();
            if (surcharge != null) {
                bookingDetail.setTotal((bookingDetail.getUnitPrice().add(surcharge.getSurcharge()).multiply(BigDecimal.valueOf(bookingDetail.getQuantity()))));
            } else {
                bookingDetail.setTotal(bookingDetail.getUnitPrice().multiply(BigDecimal.valueOf(bookingDetail.getQuantity())));
            }
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

    @PostMapping("/bill/{bookingId}/Payment")
    public String payment(@PathVariable("bookingId") Integer bookingId, Model model, RedirectAttributes redirectAttributes) {
        Booking booking = bookingService.getById(bookingId);
        return "views/booking/bill";
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/history")
    public String history(@RequestParam(required = false) Map<String, String> params, Model model, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("currentUser");
        Account account = accountService.getAccountByUsername(user.getUsername());
        Customer customer = customerService.getCustomerByAccount(account.getId());
        List<Booking> bookings = new ArrayList<>();
        SearchHistoryDTO searchHistoryDTO = new SearchHistoryDTO();


        System.out.println("Customer: " + customerService.getCustomerByAccount(account.getId()));

        int totalPages, totalItems;
        List<Integer> pageNumbers;
        Page<Booking> bookingPage = null;

        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        int size = Integer.parseInt(params.getOrDefault("size", "5"));
        Pageable pageable = PageRequest.of(page - 1, size);

        String rangeDate = params.getOrDefault("daterange", null);
        String statusPayment = params.getOrDefault("statusPayment", null);
        LocalDate fromDate, toDate;
        Boolean payment;
        if (rangeDate != null) {
            String[] date;
            date = rangeDate.split("-");
            System.out.println(date[0].trim());
            System.out.println(date[1].trim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            fromDate = LocalDate.parse(date[0].trim(), formatter);
            toDate = LocalDate.parse(date[1].trim(), formatter);
            if (statusPayment != null) {
                payment = Boolean.valueOf(statusPayment);
                bookingPage = bookingService.findByCustomerId(customer.getId(), fromDate, toDate, payment, pageable);
            } else {
                bookingPage = bookingService.findBookingByCustomer(customer.getId(), fromDate, toDate, pageable);
            }
        }


        bookingPage = bookingService.findByCustomerId(customer.getId(), pageable);


        if (bookingPage != null) {
            bookings = bookingPage.getContent();
            totalPages = bookingPage.getTotalPages();
            totalItems = Math.toIntExact(bookingPage.getTotalElements());

            if (totalPages > 0) {
                pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalItems);
        }

        bookings.forEach(booking -> {
            System.out.println("Date " + booking.getBookingDate() + "Total " + booking.getTotal());
        });
        model.addAttribute("bookings", bookings);

//        Account account
        return "views/booking/history";
    }

}
