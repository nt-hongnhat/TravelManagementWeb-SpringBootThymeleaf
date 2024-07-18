package com.nthn.springbootthymeleaf.controller.user;

import com.nthn.springbootthymeleaf.dto.SearchHistoryDTO;
import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.Booking;
import com.nthn.springbootthymeleaf.entity.Customer;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private CustomerService customerService;
    
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("")
    public String history(@RequestParam(required = false) Map<String, String> params, Model model,
                          HttpSession httpSession) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        String fromDate = params.getOrDefault("fromDate", LocalDate.now().format(formatter));
        System.out.println(fromDate);
        String toDate = params.getOrDefault("toDate", LocalDate.now().plusDays(1).format(formatter));
        System.out.println(toDate);
        String statusPayment = params.getOrDefault("statusPayment", null);
        
        SearchHistoryDTO searchHistoryDTO = new SearchHistoryDTO(LocalDate.parse(fromDate, formatter),
                                                                 LocalDate.parse(toDate, formatter), statusPayment);
        System.out.println(searchHistoryDTO);
        
        model.addAttribute("searchHistory", searchHistoryDTO);
        
        User user = (User) httpSession.getAttribute("currentUser");
        Account account = accountService.getAccountByUsername(user.getUsername());
        Customer customer = customerService.getCustomerByAccount(account.getId());
        List<Booking> bookings = new ArrayList<>();
        
        int totalPages, totalItems;
        List<Integer> pageNumbers;
        Page<Booking> bookingPage = null;
        
        
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        int size = Integer.parseInt(params.getOrDefault("size", "5"));
        Pageable pageable = PageRequest.of(page - 1, size);
        
        bookingPage = bookingService.findByCustomerId(customer.getId(), searchHistoryDTO, pageable);
        
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
        return "views/booking/history";
    }
}
