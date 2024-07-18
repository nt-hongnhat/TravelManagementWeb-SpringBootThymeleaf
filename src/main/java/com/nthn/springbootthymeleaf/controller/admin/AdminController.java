package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.entity.Booking;
import com.nthn.springbootthymeleaf.entity.Customer;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import com.nthn.springbootthymeleaf.service.PaymentService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.StatisticService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(EndpointConstants.DASHBOARD)
//@RolesAllowed({"ADMIN"})
@PreAuthorize("hasRole('ADMIN')")
//@Secured("ADMIN")
@RequiredArgsConstructor
public class AdminController {
	
	private final AccountService accountService;
	
	private final BookingService bookingService;
	
	private final CategoryService categoryService;
	
	private final CustomerService customerService;
	
	private final PaymentService paymentService;
	
	private final PermissionService permissionService;
	
	private final ProvinceService provinceService;
	
	private final StatisticService statisticService;
	
	private final TourService tourService;
	
	@GetMapping
	public String index(Model model, Principal principal) {
		if (Objects.isNull(principal)) {
			return "forward:/login";
		}
		
		final User user = (User) ((Authentication) principal).getPrincipal();
		final LocalDateTime now = LocalDateTime.now();
		final List<Object[]> objects = statisticService.getRevenueMonthlyByYear(now.getYear());
		final Map<Integer, BigDecimal> chartAreaData = new LinkedHashMap<>();
		for (int i = 1; i < 13; i++) {
			BigDecimal total = (BigDecimal) statisticService.getRevenueMonthly(i, now.getYear())
					.getFirst()[1];
			if (Objects.isNull(total)) {
				total = BigDecimal.valueOf(0);
			}
			chartAreaData.put(i, total);
		}
		
		model.addAllAttributes(
				Map.of(AttributeName.CHART_AREA_DATA, chartAreaData, AttributeName.PROFILE,
						WebUtils.toString(user)));
		return "views/admin/index";
	}
	
	@GetMapping("/bookings")
	public String bookings(Model model) {
		List<Booking> bookings = bookingService.getAll();
		model.addAttribute("bookings", bookings);
		return "views/admin/booking/list";
	}
	
	@GetMapping("/bookings/{id}")
	public String booking(@PathVariable Integer id, Model model) {
		Booking booking = bookingService.getById(id);
		model.addAttribute("booking", booking);
		return "views/admin/booking/edit";
	}
	
	@PostMapping("/bookings/{id}")
	public String bookingSave(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("booking") Booking booking, RedirectAttributes redirectAttributes)
			throws InvocationTargetException, IllegalAccessException {
		System.out.println("SIZE:" + booking.getBookingDetails().size());
		System.out.println("Booking: " + booking);
		Customer customer = customerService.getCustomerByPhone(booking.getCustomer().getPhone());
		if (customer != null) {
			booking.setCustomer(customer);
		} else {
			customer = new Customer();
			BeanUtils.copyProperties(booking.getCustomer(), customer);
			customer.setAccount(null);
			customer = customerService.addCustomer(customer);
		}
		System.out.println("customer:" + customer);
		booking.setCustomer(customer);
		bookingService.update(id, booking);
		
		redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
		return "redirect:/dashboard/bookings/" + id + "?success";
	}
	
	@GetMapping("/bookings/{id}/delete")
	public String delete(@PathVariable Integer id) {
		bookingService.delete(id);
		return "redirect:/dashboard/bookings";
	}
	
	@ModelAttribute
	public void commonAttributes(@NotNull Model model, @NotNull HttpSession httpSession) {
		final LocalDateTime now = LocalDateTime.now();
		final List<Booking> bookings = bookingService.getAll();
		model.addAllAttributes(
				Map.of(AttributeName.COUNT_BOOKINGS, bookings.size(), AttributeName.COUNT_BOOKINGS_NOT_PAID,
						bookings.stream().filter(booking -> Objects.isNull(booking.getPayment())).count(),
						AttributeName.COUNT_BOOKINGS_PAID,
						bookings.stream().filter(booking -> Objects.nonNull(booking.getPayment())).count()));
		model.addAllAttributes(Map.of(AttributeName.REVENUE_ANNUAL,
				statisticService.getRevenueAnnual(now.getYear()).getFirst(), AttributeName.REVENUE_MONTHLY,
				statisticService.getRevenueMonthly(now.getMonth().getValue(), now.getYear()).getFirst()));
		model.addAttribute(Map.of(AttributeName.NOW, now, AttributeName.CURRENT_USER,
				accountService.getAccountByUsername(
						((User) httpSession.getAttribute(AttributeName.CURRENT_USER)).getUsername())));
		model.addAllAttributes(
				(Collection<?>) Map.of(AttributeName.CATEGORIES, this.categoryService.getCategories(),
						AttributeName.PAYMENTS, this.paymentService.findAll(), AttributeName.PROVINCES,
						this.provinceService.getProvinces(), AttributeName.TOURS, this.tourService.getTours()));
	}
}
