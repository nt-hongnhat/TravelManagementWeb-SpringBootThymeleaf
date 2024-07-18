package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.constants.CurrencyConstants;
import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.ViewPath;
import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.Booking;
import com.nthn.springbootthymeleaf.entity.BookingDetail;
import com.nthn.springbootthymeleaf.entity.Customer;
import com.nthn.springbootthymeleaf.entity.Payment;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.entity.TourTicket;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.BookingDetailService;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import com.nthn.springbootthymeleaf.service.FeedbackService;
import com.nthn.springbootthymeleaf.service.PaymentService;
import com.nthn.springbootthymeleaf.service.PaypalService;
import com.nthn.springbootthymeleaf.service.PlacesService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.TourGroupService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.service.TourTicketService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
@PreAuthorize("isAuthenticated()") //Phải đảm bảo là user login để có principal.id
@RequiredArgsConstructor
public class BookingController {
	
	private final TourService tourService;
	
	private final BookingService bookingService;
	
	private final TourGroupService tourGroupService;
	
	private final ProvinceService provinceService;
	
	private final FeedbackService feedbackService;
	
	private final PlacesService placesService;
	
	private final BookingDetailService bookingDetailService;
	
	private final CustomerService customerService;
	
	private final AccountService accountService;
	
	private final TourTicketService tourTicketService;
	
	private final PaypalService paypalService;
	
	private final PaymentService paymentService;
	
	private final DepartureDateService departureDateService;
	
	private Account account;
	
	@ModelAttribute
	public void commonAttributes(@NotNull Model model, Principal principal) {
		if (Objects.nonNull(principal)) {
			account = accountService.getAccountByUsername(principal.getName());
		}
		
		model.addAttribute(Map.of(AttributeName.TOUR_GROUPS, this.tourGroupService.getTourGroups(),
				AttributeName.PROVINCES, this.provinceService.getProvinces(""), AttributeName.FEEDBACKS,
				this.feedbackService.getFeedbacks(4.0), AttributeName.PLACES,
				this.placesService.getPlaces(), AttributeName.DURATIONS, this.tourService.getDurations(),
				AttributeName.CURRENT_USER, account));
	}
	
	@GetMapping("/tours/{id}/booking")
	public String index(@PathVariable("id") Integer id, Model model) {
		final Booking booking = new Booking();
		final Tour tour = tourService.getById(id);
		final List<TourTicket> tourTickets = tour.getTourTickets();
		final Customer customer = customerService.getCustomerByAccount(account.getId());
		booking.setCustomer(Objects.nonNull(customer) ? customer
						: new Customer().setFirstname(account.getFirstName())
								.setLastname(account.getLastName())
								.setAccount(account))
				.setBookingDetails(tourTickets.stream()
						.map(tourTicket -> new BookingDetail(booking, tourTicket))
						.toList());
		model.addAllAttributes(
				Map.of(AttributeName.TOUR, tour, AttributeName.BOOKING, booking, AttributeName.TOUR_TICKETS,
						tourTickets));
		return "views/booking/booking";
	}
	
	@PostMapping("/tours/{id}/booking")
	public String booking(@PathVariable("id") Integer id, @ModelAttribute("booking") Booking booking,
			Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		
		final Customer customer = customerService.getCustomerByPhone(booking.getCustomer().getPhone());
		booking.setCustomer(Objects.nonNull(customer) ? customer
				: customerService.addCustomer(booking.getCustomer().setAccount(account)));
		
		if (result.hasErrors()) {
			model.addAttribute(AttributeName.BOOKING, booking);
			redirectAttributes.addFlashAttribute(AttributeName.ERROR_MESSAGE,
					result.getAllErrors().toString());
			return "redirect:/tours/{id}/booking?error";
		}
		
		redirectAttributes.addFlashAttribute(AttributeName.SUCCESS_MESSAGE, "Đặt tour thành công!");
		return "redirect:/booking/" + bookingService.addBooking(booking).getId();
	}
	
	
	@GetMapping(EndpointConstants.BOOKING)
	public String payment(@PathVariable Integer id, Model model) {
		final Booking booking = bookingService.getById(id);
		
		model.addAllAttributes(Map.of(AttributeName.BOOKING, booking, AttributeName.PAYMENTS,
				paymentService.findAll().stream().peek(payment -> {
					final BigDecimal total = booking.getTotal();
					if (Objects.equals(payment.getCurrency(), CurrencyConstants.VND)) {
						payment.setTotal(total.doubleValue());
					} else if (Objects.equals(payment.getCurrency(), CurrencyConstants.USD)) {
						payment.setTotal(
								total.divide(BigDecimal.valueOf(25345), 2, RoundingMode.HALF_UP).doubleValue());
					}
				}).toList(), AttributeName.PAYMENT, new Payment()));
		return ViewPath.BILL;
	}
}
