package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DepartureDateController {

  private final AccountService accountService;
  private final DepartureDateService departureDateService;
  private final TourService tourService;
		
	@GetMapping("/tours/{id}/departure-date/add")
  public String add(Model model, @PathVariable Integer id) {

    model.addAttribute("departureDate", new DepartureDate().setTour(tourService.getById(id)));
    return "views/admin/departureDate/add";
  }

  @PostMapping("/tours/{tourId}/departure-date/add")
  public String add(
      @PathVariable Integer tourId,
      @ModelAttribute DepartureDate departureDate,
      RedirectAttributes redirectAttributes) {

    final Tour tour = tourService.getById(tourId);

    departureDate.setTour(tour);
    departureDate.setAvailableSlot(tour.getMaxSlot());
    departureDate.setDate(WebUtils.getLocalDate(departureDate.getValueDate()));

    departureDateService.create(departureDate);

    redirectAttributes.addFlashAttribute(
        "successMessage", "Ngày khởi hành đã được thêm thành công!");

    return "redirect:/dashboard/tours/{id}";
  }

  @ModelAttribute
  public void addAttributes(
      @NotNull Model model, @NotNull HttpSession httpSession) {
	  	  
	  model.addAllAttributes(
        Map.of(
            "tours",
            tourService.getTours(),
            "currentUser",
            accountService.getAccountByUsername(
                ((User) httpSession.getAttribute("currentUser")).getUsername())));
  }

  @GetMapping("/tours/{tourId}/departure-date/{departureDateId}")
  public String delete(@PathVariable Integer tourId, @PathVariable Integer departureDateId) {
    departureDateService.delete(departureDateId);
    return "redirect:/dashboard/tours/{tourId}";
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("departureDate", new DepartureDate());
    return "views/admin/departureDate/add";
  }
}
