package com.nthn.springbootthymeleaf.controller.guest;

import com.nthn.springbootthymeleaf.dto.SearchTourDTO;
import com.nthn.springbootthymeleaf.entity.*;
import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private static final int DEFAULT_SIZE = 4;

  private final AccountService accountService;
  private final CategoryService categoryService;
  private final CustomerService customerService;
  private final FeedbackService feedbackService;
  private final NewsService newsService;
  private final PlacesService placesService;
  private final ProvinceService provinceService;
  private final TourGroupService tourGroupService;
  private final TourService tourService;

  @GetMapping("/about")
  public String about(Model model) {
    return "views/about";
  }

  @ModelAttribute
  public void commonAttributes(@NotNull Model model, @NotNull HttpSession httpSession) {
    final List<Province> provinces = provinceService.getProvinces();

    model
        .addAttribute("departurePlaces", provinces.stream().filter(isSupportedProvince()).toList())
        .addAttribute("now", LocalDate.now())
        .addAttribute("categories", categoryService.getCategories())
        .addAttribute("tourGroups", tourGroupService.getTourGroups())
        .addAttribute("provinces", provinces)
        .addAttribute("feedbacks", feedbackService.getFeedbacks(4.0))
        .addAttribute("places", placesService.getPlaces())
        .addAttribute("searchTourDTO", new SearchTourDTO())
        .addAttribute("currentUser", httpSession.getAttribute("currentUser"));
  }

  private Predicate<Province> isSupportedProvince() {
    return province -> List.of("Hồ Chí Minh", "Hà Nội", "Cần Thơ").contains(province.getName());
  }

  @GetMapping("/contact")
  public String contact(Model model) {
    return "views/contact";
  }

  // Xem chi tiết tin tức
  // GET: /news/{id}
  @GetMapping("/news/{id}")
  public String detail(@PathVariable("id") Integer id, Model model, Principal principal) {

    if (Objects.nonNull(principal)) {
      model.addAttribute("account", accountService.getAccountByUsername(principal.getName()));
    }

    final News news = newsService.getById(id);

    model.addAttribute("news", news).addAttribute("comments", news.getComments());

    return "views/news/details";
  }

  // Hiển thị trang tin tức
  // GET: /news
  @GetMapping("/news")
  public String getNews(@RequestParam(required = false) Map<String, String> params, Model model) {

    final int currentPage = Integer.parseInt(params.getOrDefault("page", "1"));
    final Page<News> newsPage =
        newsService.getNewsPage(
            PageRequest.of(currentPage - 1, Integer.parseInt(params.getOrDefault("size", "4"))));
    final List<News> news = newsPage.getContent();
    final int totalPages = newsPage.getTotalPages();

    if (totalPages > 0) {
      model.addAttribute("pageNumbers", IntStream.rangeClosed(1, totalPages).boxed().toList());
    }

    model
        .addAttribute(
            "topNews", news.stream().max(Comparator.comparing(News::getViews)).orElse(null))
        .addAttribute("currentPage", currentPage)
        .addAttribute("totalPages", totalPages)
        .addAttribute("totalItems", Math.toIntExact(newsPage.getTotalElements()))
        .addAttribute("newsList", news);

    return "views/news/list";
  }

  @GetMapping("/tours/{id}")
  public String getTourDetail(@PathVariable("id") Integer id, Model model) {
    final Tour tour = tourService.getById(id);

    model
        .addAttribute("tour", tour)
        .addAttribute("tourSchedules", tour.getTourSchedules())
        .addAttribute("tourGroup", tour.getTourGroup());

    return "views/tour/details";
  }

  @GetMapping("")
  public String index(Model model, Principal principal) {
    String profile = null;
    if (Objects.nonNull(principal)) {
      profile = WebUtils.toString((User) ((Authentication) principal).getPrincipal());
    }

    model
        .addAttribute("countTours", tourService.getTours().size())
        .addAttribute("countTourist", customerService.getCustomers().size())
        .addAttribute("countPlaces", placesService.getPlaces().size())
        .addAttribute("countNews", newsService.getNews().size())
        .addAttribute("searchTourDTO", new SearchTourDTO())
        .addAttribute("profile", profile);

    return "views/index";
  }

  /**
   * Tìm kiếm tour theo nhiều tiêu chí
   *
   * @param params
   * @param searchTourDTO form tìm kiếm
   * @param model
   * @return views/tour/list.html
   */
  @PostMapping("/tours")
  public String searchTours(
      @RequestParam(required = false) Map<String, String> params,
      @ModelAttribute("searchTourDTO") SearchTourDTO searchTourDTO,
      Model model) {
    final List<Tour> tours = new ArrayList<>();
    final int page = Integer.parseInt(params.getOrDefault("page", "1"));
    final Page<Tour> tourPage =
        tourService.getTourPageBySearchTour(
            searchTourDTO.setRangeDate(params.get("daterange")),
            PageRequest.of(
                page - 1,
                Integer.parseInt(params.getOrDefault("size", String.valueOf(DEFAULT_SIZE)))));

    if (tourPage.hasContent()) {
      tours.addAll(tourPage.getContent());
    }

    final int totalPages = tourPage.getTotalPages();

    if (totalPages > 0) {
      model.addAttribute("pageNumbers", IntStream.rangeClosed(1, totalPages).boxed().toList());
    }

    model
        .addAttribute("currentPage", page)
        .addAttribute("totalPages", totalPages)
        .addAttribute("totalItems", tours.size())
        .addAttribute("tours", tours)
        .addAttribute("tourPage", tourPage);

    return "views/tour/list";
  }

  @GetMapping("/services")
  public String services(Model model) {
    return "views/services";
  }
}
