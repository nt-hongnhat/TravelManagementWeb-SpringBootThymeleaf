package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import com.nthn.springbootthymeleaf.service.TourService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
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
	
	private final DepartureDateService departureDateService;
	
	private final TourService tourService;
	
	private final AccountService accountService;
	
	@ModelAttribute
	public void addAttributes(@NotNull Model model, HttpServletRequest request,
			@NotNull HttpSession httpSession) {
		User currentUser = (User) httpSession.getAttribute("currentUser");
		Account account = accountService.getAccountByUsername(currentUser.getUsername());
		
		model.addAttribute("tours", tourService.getTours());
		model.addAttribute("currentUser", account);
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("departureDate", new DepartureDate());
		return "views/admin/departureDate/add";
	}
	
	@GetMapping("/tours/{id}/departure-date/add")
	public String add(Model model, @PathVariable Integer id) {
		Tour tour = tourService.getById(id);
		DepartureDate departureDate = new DepartureDate();
		departureDate.setTour(tour);
		model.addAttribute("departureDate", departureDate);
		return "views/admin/departureDate/add";
	}
	
	@PostMapping("/tours/{id}/departure-date/add")
	public String add(@ModelAttribute DepartureDate departureDate,
			RedirectAttributes redirectAttributes) {
		Tour tour = tourService.getById(departureDate.getTour().getId());
		departureDate.setTour(tour);
		departureDate.setAvailableSlot(tour.getMaxSlot());
		departureDate.setDate(
				LocalDate.parse(departureDate.getValueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		departureDateService.create(departureDate);
		redirectAttributes.addFlashAttribute("successMessage",
				"Ngày khởi hành đã được thêm thành công!");
		return "redirect:/dashboard/tours/{id}";
	}
	
	@GetMapping("/tours/{tourId}/departure-date/{departureDateId}")
	public String delete(@PathVariable Integer tourId, @PathVariable Integer departureDateId) {
		departureDateService.delete(departureDateId);
		return "redirect:/dashboard/tours/{tourId}";
	}
}
