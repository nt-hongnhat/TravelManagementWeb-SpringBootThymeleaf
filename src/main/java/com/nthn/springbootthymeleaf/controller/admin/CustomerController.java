package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.FormatConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.ViewPath;
import com.nthn.springbootthymeleaf.entity.Customer;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Validated
@Controller
@RequestMapping(EndpointConstants.DASHBOARD + EndpointConstants.CUSTOMERS)
@RequiredArgsConstructor
public class CustomerController {
	
	private final AccountService accountService;
	
	private final BookingService bookingService;
	
	private final CategoryService categoryService;
	
	private final CustomerService customerService;
	
	@ModelAttribute
	public void commonAttribute(Model model, HttpSession httpSession) {
		final User currentUser = (User) httpSession.getAttribute(AttributeName.CURRENT_USER);
		model.addAllAttributes(Map.of(AttributeName.CURRENT_USER, currentUser, AttributeName.AVATAR,
				accountService.getAccountByUsername(currentUser.getUsername()).getPhotosImagePath()));
	}
	
	@GetMapping(EndpointConstants.DEFAULT)
	public String index(Model model) {
		model.addAttribute(AttributeName.CUSTOMERS, customerService.getCustomers());
		return ViewPath.CUSTOMER_LIST;
	}
	
	@GetMapping(EndpointConstants.CREATE)
	public String create(Model model) {
		model.addAttribute(AttributeName.CUSTOMER, new Customer());
		return ViewPath.CUSTOMER_CREATE;
	}
	
	@PostMapping(EndpointConstants.CREATE)
	public String save(@Valid @ModelAttribute Customer customer, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(AttributeName.ERROR_MESSAGE, "Đã xảy ra lỗi!!! Thử lại");
			return "redirect:/admin/customers/create?error";
		}
		
		customerService.save(customer);
		redirectAttributes.addFlashAttribute(AttributeName.SUCCESS_MESSAGE,
				"Thêm khách hàng thành công!");
		return "redirect:/admin/customers/create?success";
	}
	
	
	@GetMapping(EndpointConstants.PATH_VARIABLE_ID)
	public String edit(@Valid @NotNull @PathVariable Integer id, Model model) {
		final Customer customer = customerService.getCustomerById(id);
		model.addAllAttributes(
				Map.of(AttributeName.CUSTOMER, customer, AttributeName.ACCOUNT, customer.getAccount()));
		return ViewPath.CUSTOMER_EDIT;
	}
	
	@PostMapping(EndpointConstants.PATH_VARIABLE_ID)
	public String update(@PathVariable Integer id, @ModelAttribute Customer customer,
			RedirectAttributes redirectAttributes) {
		customerService.update(customer.getId(), customer);
		redirectAttributes.addFlashAttribute(AttributeName.SUCCESS_MESSAGE,
				"Cập nhật hồ sơ khách hàng thành công!");
		return String.format("%s%s%s", ViewPath.REDIRECT, ViewPath.CUSTOMERS,
				EndpointConstants.PATH_VARIABLE_ID);
	}
	
	
	@GetMapping(EndpointConstants.PATH_VARIABLE_ID)
	public String details(@PathVariable Integer id, Model model) {
		final LocalDateTime now = LocalDateTime.now();
		final int currentMonth = now.getMonthValue();
		final int currentYear = now.getYear();
		final List<Object[]> stats = bookingService.sumBookingTotalInMonthByCustomerId(id, currentMonth,
				currentYear);
		
		final Map<String, BigDecimal> totals = new LinkedHashMap<>();
		final int daysOfMonth = now.getDayOfMonth();
		IntStream.range(0, daysOfMonth).<Consumer<? super Object[]>>mapToObj(finalI -> stat -> {
			int day = (int) stat[0];
			if (Objects.equals(day, finalI)) {
				totals.put(LocalDate.of(currentYear, currentMonth, day)
						.format(DateTimeFormatter.ofPattern(FormatConstants.DATE_VN)), (BigDecimal) stat[1]);
			} else {
				totals.put(LocalDate.of(currentYear, currentMonth, finalI + 1)
						.format(DateTimeFormatter.ofPattern(FormatConstants.DATE_VN)), BigDecimal.valueOf(0));
			}
		}).forEach(stats::forEach);
		
		model.addAllAttributes(
				Map.of(AttributeName.CUSTOMER, customerService.getCustomerById(id), AttributeName.BOOKINGS,
						bookingService.getBookingsByCustomer(id), AttributeName.STATS, stats,
						AttributeName.TIME,
						String.join("/", String.valueOf(currentMonth), String.valueOf(currentYear)),
						AttributeName.TOTALS, totals, AttributeName.KEY_SET, totals.keySet(),
						AttributeName.VALUES, totals.values()));
		
		return "views/admin/customer/details";
	}
	
	
}
