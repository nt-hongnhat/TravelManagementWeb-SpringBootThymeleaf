package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.FormatConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.entity.Province;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.entity.TourSchedule;
import com.nthn.springbootthymeleaf.entity.TourTicket;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.CloudinaryService;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import com.nthn.springbootthymeleaf.service.FeedbackService;
import com.nthn.springbootthymeleaf.service.NewsService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import com.nthn.springbootthymeleaf.service.PlacesService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.SurchargeService;
import com.nthn.springbootthymeleaf.service.TourGroupService;
import com.nthn.springbootthymeleaf.service.TourScheduleService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.service.TourTicketService;
import com.nthn.springbootthymeleaf.validation.TourValidator;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/tours")
@RequiredArgsConstructor
public class TourController {
	
	private final AccountService accountService;
	
	private final BookingService bookingService;
	
	private final CategoryService categoryService;
	
	private final CloudinaryService cloudinaryService;
	
	private final DepartureDateService departureDateService;
	
	private final FeedbackService feedbackService;
	
	private final MailProperties mailProperties;
	
	private final MessageSource messageSource;
	
	private final NewsService newsService;
	
	private final PermissionService permissionService;
	
	private final PlacesService placesService;
	
	private final ProvinceService provinceService;
	
	private final SurchargeService surchargeService;
	
	private final TourGroupService tourGroupService;
	
	private final TourScheduleService tourScheduleService;
	
	private final TourService tourService;
	
	private final TourTicketService tourTicketService;
	
	private final TourValidator tourValidator;
	
	@InitBinder
	protected void initBinder(@NotNull WebDataBinder binder) {
		
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println(target);
		if (target.getClass() == Tour.class) {
			binder.setValidator(tourValidator);
		}
	}
	
	@ModelAttribute
	public void commonAttributes(@NotNull Model model, @NotNull HttpSession httpSession) {
		
		final Account account = accountService.getAccountByUsername(
				((User) httpSession.getAttribute("currentUser")).getUsername());
		final List<Province> provinces = provinceService.getProvinces();
		
		model.addAllAttributes(
				Map.of("today", LocalDateTime.now(), "categories", categoryService.getCategories(),
						"tourGroups", tourGroupService.getTourGroups(), "provinces", provinces,
						"currentUser", account, "avatar", account.getPhotosImagePath(),
						"departurePlaces", provinces.stream()
						                            .filter(province -> List.of("Hồ Chí Minh",
								                                                    "Hà Nội", "Cần"
										                                                    + " Thơ")
						                                                    .contains(
								                                                    province.getName()))
						                            .toList()));
	}
	
	@GetMapping
	public String getTours(Model model) {
		
		List<Tour> tours = tourService.getTours();
		model.addAttribute("tours", tours);
		return "views/admin/tour/list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Tour tour = new Tour();
		List<DepartureDate> departureDates = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			departureDates.add(new DepartureDate());
		}
		tour.setDepartureDates(departureDates);
		model.addAttribute("tour", tour);
		
		return "views/admin/tour/create";
	}
	
	@PostMapping("/create")
	public String save(@RequestParam(value = "images") MultipartFile[] multipartFiles,
			@Valid @ModelAttribute("tour") Tour tour, BindingResult result,
			final RedirectAttributes redirectAttributes) throws ParseException {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute(AttributeName.ERROR_MESSAGE,
					result.getAllErrors());
			redirectAttributes.addFlashAttribute(AttributeName.TOUR, tour);
			
			return "redirect:/dashboard/tours/create?error";
		}
		
		if (ObjectUtils.isEmpty(multipartFiles)) {
			redirectAttributes.addFlashAttribute(AttributeName.TOUR, tour);
			redirectAttributes.addFlashAttribute(AttributeName.ERROR_MESSAGE,
					"Vui lòng chọn ít nhất 1 ảnh");
			
			return "redirect:/dashboard/tours/create?error";
		}
		
		final List<String> images = new ArrayList<>();
		Arrays.stream(multipartFiles).toList().forEach(multipartFile -> {
			if (Objects.nonNull(multipartFile)) {
				images.add(cloudinaryService.upload(multipartFile).get("url").toString() + " ");
			}
		});
		
		tour.setImage(String.join(" ", images));
		
		tourService.create(tour);
		redirectAttributes.addFlashAttribute(AttributeName.TOUR, tour);
		redirectAttributes.addFlashAttribute(AttributeName.SUCCESS_MESSAGE, "Thêm thành công!");
		
		return "redirect:/dashboard/tours/" + tour.getId();
	}
	
	@GetMapping(EndpointConstants.PATH_VARIABLE_ID)
	public String details(Model model, @PathVariable("id") Integer tourId) {
		
		final Tour tour = tourService.getById(tourId);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatConstants.DATE_VN);
		final List<DepartureDate> departureDates = tour.getDepartureDates()
		                                               .stream()
		                                               .map(departureDate -> departureDate.setValueDate(
				                                               departureDate.getDate()
				                                                            .format(formatter)))
		                                               .sorted(Comparator.comparing(
				                                               DepartureDate::getDate))
		                                               .toList();
		tour.setDepartureDates(departureDates);
		model.addAllAttributes(
				Map.of("formatter", formatter, "departureDates", departureDates,
						AttributeName.TOUR,
						tour));
		return "views/admin/tour/details";
	}
	
	@PostMapping(EndpointConstants.PATH_VARIABLE_ID)
	public String update(
			@RequestParam(value = "images", required = false) MultipartFile[] multipartFiles,
			@PathVariable("id") Integer tourId, @Valid @ModelAttribute("tour") Tour tour,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
			redirectAttributes.addFlashAttribute("errorsName", result.getFieldErrors("name"));
			redirectAttributes.addFlashAttribute("tour", tour);
			return "redirect:/dashboard/tours/{id}";
		}
		
		List<TourTicket> tourTickets = tour.getTourTickets();
		tourTickets.forEach(tourTicket -> {
			tourTicket.setTour(tour);
			tourTicketService.update(tourTicket.getId(), tourTicket);
		});
		List<TourSchedule> tourSchedules = tour.getTourSchedules();
		tourSchedules.forEach(tourSchedule -> {
			tourSchedule.setTour(tour);
			tourScheduleService.update(tourSchedule.getId(), tourSchedule);
		});
		
		final List<DepartureDate> departureDates = tour.getDepartureDates();
		departureDates.forEach(departureDate -> {
			departureDate.setTour(tour);
			departureDate.setDate(LocalDate.parse(departureDate.getValueDate(),
					DateTimeFormatter.ofPattern(FormatConstants.YYYY_MM_DD)));
			departureDateService.update(departureDate.getId(), departureDate);
		});
		
		if (multipartFiles != null) {
			List<String> images = new ArrayList<>();
			Arrays.stream(multipartFiles).toList().forEach(multipartFile -> {
				if (multipartFile != null) {
					Map map = cloudinaryService.upload(multipartFile);
					images.add(map.get("url").toString() + " ");
				}
			});
			
			if (tour.getImage() == null) {
				tour.setImage("");
			}
			images.forEach(s -> tour.setImage(tour.getImage() + s));
			
		}
		
		tourService.update(tourId, tour);
		
		redirectAttributes.addFlashAttribute("success", "Cập nhật thành công");
		redirectAttributes.addFlashAttribute("tour", tour);
		
		return "redirect:/dashboard/tours/{id}?success";
	}
	
	@DeleteMapping("/{id}/delete")
	public String deleteTourById(@PathVariable("id") Integer id) {
		
		tourService.delete(id);
		return "redirect:/dashboard/tours/";
	}
}
