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
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.StatisticService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
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
// @RolesAllowed({"ADMIN"})
@PreAuthorize("hasRole('ADMIN')")
// @Secured("ADMIN")
@RequiredArgsConstructor
public class AdminController {

  private final AccountService accountService;
  private final BookingService bookingService;
  private final CategoryService categoryService;
  private final CustomerService customerService;
  private final PaymentService paymentService;
  private final ProvinceService provinceService;
  private final StatisticService statisticService;
  private final TourService tourService;

  @GetMapping("/bookings/{id}")
  public String booking(@PathVariable Integer id, Model model) {
    model.addAttribute("booking", bookingService.getById(id));
    return "views/admin/booking/edit";
  }

  @PostMapping("/bookings/{id}")
  public String bookingSave(
      @PathVariable Integer id,
      @Valid @ModelAttribute Booking booking,
      RedirectAttributes redirectAttributes) {

    Customer customer = customerService.getCustomerByPhone(booking.getCustomer().getPhone());
    if (Objects.isNull(customer)) {
      customer = customerService.addCustomer(customer);
    }

    bookingService.update(id, booking.setCustomer(customer));

    redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");

    return "redirect:/dashboard/bookings/" + id + "?success";
  }

  @GetMapping("/bookings")
  public String bookings(Model model) {
    model.addAttribute("bookings", bookingService.getAll());
    return "views/admin/booking/list";
  }

  @ModelAttribute
  public void commonAttributes(@NotNull Model model, @NotNull HttpSession httpSession) {
    final LocalDateTime now = LocalDateTime.now();
    final int currentYear = now.getYear();
    final List<Booking> bookings = bookingService.getAll();

    model.addAllAttributes(
        (Collection<?>)
            Map.of(
                AttributeName.COUNT_BOOKINGS,
                bookings.size(),
                AttributeName.COUNT_BOOKINGS_NOT_PAID,
                bookings.stream().filter(booking -> Objects.isNull(booking.getPayment())).count(),
                AttributeName.REVENUE_ANNUAL,
                statisticService.getRevenueAnnual(currentYear).getFirst(),
                AttributeName.REVENUE_MONTHLY,
                statisticService
                    .getRevenueMonthly(now.getMonth().getValue(), currentYear)
                    .getTotal(),
                AttributeName.NOW,
                now,
                AttributeName.CURRENT_USER,
                accountService.getAccountByUsername(
                    ((User) httpSession.getAttribute(AttributeName.CURRENT_USER)).getUsername()),
                AttributeName.CATEGORIES,
                categoryService.getCategories(),
                AttributeName.PAYMENTS,
                paymentService.findAll(),
                AttributeName.PROVINCES,
                provinceService.getProvinces(),
                AttributeName.TOURS,
                tourService.getTours()));
  }

  @GetMapping("/bookings/{id}/delete")
  public String delete(@PathVariable Integer id) {
    bookingService.delete(id);
    return "redirect:/dashboard/bookings";
  }

  @GetMapping
  public String index(Model model, Principal principal) {
    if (Objects.isNull(principal)) {
      return "forward:/login";
    }

    final int currentYear = LocalDateTime.now().getYear();
    final Map<Integer, BigDecimal> chartAreaData = new LinkedHashMap<>();
    for (int i = 1; i < 13; i++) {
      chartAreaData.put(i, statisticService.getRevenueMonthly(i, currentYear).getTotal());
    }

    model.addAllAttributes(
        Map.of(
            AttributeName.CHART_AREA_DATA,
            chartAreaData,
            AttributeName.PROFILE,
            WebUtils.toString((User) ((Authentication) principal).getPrincipal())));

    return "views/admin/index";
  }
}
