package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.FormatConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.entity.Province;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.CloudinaryService;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.TourGroupService;
import com.nthn.springbootthymeleaf.service.TourScheduleService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.service.TourTicketService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import com.nthn.springbootthymeleaf.validator.TourValidator;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
  private final CategoryService categoryService;
  private final CloudinaryService cloudinaryService;
  private final DepartureDateService departureDateService;
  private final ProvinceService provinceService;
  private final TourGroupService tourGroupService;
  private final TourScheduleService tourScheduleService;
  private final TourService tourService;
  private final TourTicketService tourTicketService;
  private final TourValidator tourValidator;

  @ModelAttribute
  public void commonAttributes(@NotNull Model model, @NotNull HttpSession httpSession) {

    final Account account =
        accountService.getAccountByUsername(
            ((User) httpSession.getAttribute("currentUser")).getUsername());

    final List<Province> provinces = provinceService.getProvinces();

    model.addAllAttributes(
        Map.of(
            "today",
            LocalDateTime.now(),
            "categories",
            categoryService.getCategories(),
            "tourGroups",
            tourGroupService.getTourGroups(),
            "provinces",
            provinces,
            "currentUser",
            account,
            "avatar",
            account.getPhotosImagePath(),
            "departurePlaces",
            provinces.stream().filter(isSupportedProvince()).toList()));
  }

  private Predicate<Province> isSupportedProvince() {
    return province -> List.of("Hồ Chí Minh", "Hà Nội", "Cần Thơ").contains(province.getName());
  }

  @GetMapping("/create")
  public String create(Model model) {

    final List<DepartureDate> departureDates = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      departureDates.add(i, new DepartureDate());
    }

    model.addAttribute("tour", new Tour().setDepartureDates(departureDates));

    return "views/admin/tour/create";
  }

  @DeleteMapping("/{id}/delete")
  public String deleteTourById(@PathVariable("id") Integer id) {

    tourService.delete(id);
    return "redirect:/dashboard/tours/";
  }

  @GetMapping(EndpointConstants.PATH_VARIABLE_ID)
  public String details(Model model, @PathVariable("id") Integer tourId) {

    final Tour tour = tourService.getById(tourId);
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatConstants.DATE_VN);
    final List<DepartureDate> departureDates =
        tour.getDepartureDates().stream()
            .map(
                departureDate ->
                    departureDate.setValueDate(departureDate.getDate().format(formatter)))
            .sorted(Comparator.comparing(DepartureDate::getDate))
            .toList();

    tour.setDepartureDates(departureDates);

    model.addAllAttributes(
        Map.of("formatter", formatter, "departureDates", departureDates, AttributeName.TOUR, tour));

    return "views/admin/tour/details";
  }

  @GetMapping
  public String getTours(Model model) {

    model.addAttribute("tours", tourService.getTours());
    return "views/admin/tour/list";
  }

  @PostMapping("/create")
  public String save(
      @RequestParam(value = "images") MultipartFile[] multipartFiles,
      @Valid @ModelAttribute("tour") Tour tour,
      BindingResult result,
      final RedirectAttributes redirectAttributes)
      throws ParseException {

    if (result.hasErrors()) {
      redirectAttributes
          .addFlashAttribute(AttributeName.ERROR_MESSAGE, result.getAllErrors())
          .addFlashAttribute(AttributeName.TOUR, tour);

      return "redirect:/dashboard/tours/create?error";
    }

    if (ObjectUtils.isEmpty(multipartFiles)) {
      redirectAttributes
          .addFlashAttribute(AttributeName.TOUR, tour)
          .addFlashAttribute(AttributeName.ERROR_MESSAGE, "Vui lòng chọn ít nhất 1 ảnh");

      return "redirect:/dashboard/tours/create?error";
    }

    final List<String> images =
        Arrays.stream(multipartFiles)
            .filter(Objects::nonNull)
            .map(
                multipartFile -> {
                  try {
                    return cloudinaryService.upload(multipartFile).get("url").toString() + " ";
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                })
            .toList();

    tour.setImage(String.join(" ", images));

    tourService.create(tour);

    redirectAttributes
        .addFlashAttribute(AttributeName.TOUR, tour)
        .addFlashAttribute(AttributeName.SUCCESS_MESSAGE, "Thêm thành công!");

    return "redirect:/dashboard/tours/" + tour.getId();
  }

  @PostMapping(EndpointConstants.PATH_VARIABLE_ID)
  public String update(
      @RequestParam(value = "images", required = false) MultipartFile[] multipartFiles,
      @PathVariable("id") Integer tourId,
      @Valid @ModelAttribute("tour") Tour tour,
      BindingResult result,
      final RedirectAttributes redirectAttributes) {

    if (result.hasErrors()) {
      redirectAttributes
          .addFlashAttribute("errors", result.getAllErrors())
          .addFlashAttribute("errorsName", result.getFieldErrors("name"))
          .addFlashAttribute("tour", tour);
      return "redirect:/dashboard/tours/{id}";
    }

    tour.getTourTickets()
        .forEach(
            tourTicket -> tourTicketService.update(tourTicket.getId(), tourTicket.setTour(tour)));
    tour.getTourSchedules()
        .forEach(
            tourSchedule ->
                tourScheduleService.update(tourSchedule.getId(), tourSchedule.setTour(tour)));
    tour.getDepartureDates()
        .forEach(
            departureDate ->
                departureDateService.update(
                    departureDate.getId(),
                    departureDate
                        .setTour(tour)
                        .setDate(WebUtils.getLocalDate(departureDate.getValueDate()))));

    if (Objects.nonNull(multipartFiles)) {
      final List<String> images =
          Arrays.stream(multipartFiles)
              .filter(Objects::nonNull)
              .map(
                  multipartFile -> {
                    try {
                      return cloudinaryService.upload(multipartFile).get("url").toString() + " ";
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  })
              .toList();

      tour.setImage(String.join(" ", images));
    }

    tourService.update(tourId, tour);

    redirectAttributes
        .addFlashAttribute("tour", tour)
        .addFlashAttribute("success", "Tour is updated successfully");

    return "redirect:/dashboard/tours/{id}?success";
  }

  @InitBinder
  protected void initBinder(@NotNull WebDataBinder binder) {

    final Object target = binder.getTarget();
    if (target == null) {
      return;
    }

    if (target.getClass() == Tour.class) {
      binder.setValidator(tourValidator);
    }
  }
}
