package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.DTO.SearchTourDTO;
import com.nthn.springbootthymeleaf.pojo.*;
import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TourGroupService tourGroupService;
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private NewsService newsService;
    @Autowired
    private TourService tourService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PlacesService placesService;

    @ModelAttribute
    public void commonAttributes(Model model, HttpSession httpSession) {
        LocalDate now = LocalDate.now();
        List<Category> categories = categoryService.getCategories();
        List<TourGroup> tourGroups = tourGroupService.getTourGroups();
        List<Province> provinces = provinceService.getProvinces();
        List<Places> places = placesService.getPlaces();
        List<Feedback> feedbacks = feedbackService.getFeedbacks(4.0);

        SearchTourDTO searchTourDTO = new SearchTourDTO();

        model.addAttribute("now", now);
        model.addAttribute("categories", categories);
        model.addAttribute("tourGroups", tourGroups);
        model.addAttribute("provinces", provinces);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("places", places);
        model.addAttribute("searchTourDTO", searchTourDTO);
        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));

    }


    @GetMapping
    public String index(Model model, Principal principal) {
        String profile = null;
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            profile = WebUtils.toString(user);
        }

        // Đếm số chuyến du lịch
        int countTours = tourService.getTours().size();
        // Đếm số lượng khách du lịch
        int countTourist = customerService.getCustomers().size();
        // Đếm số bài viết tin tức
        int countNews = newsService.getNews().size();
        // Đếm số địa điểm tham quan
        int countPlaces = placesService.getPlaces().size();

        model.addAttribute("durations", this.tourService.getDurations());
        model.addAttribute("departurePlaces", this.tourService.getDeparturePlaces());
        model.addAttribute("countTours", countTours);
        model.addAttribute("countTourist", countTourist);
        model.addAttribute("countPlaces", countPlaces);
        model.addAttribute("countNews", countNews);

        model.addAttribute("searchTourDTO", new SearchTourDTO());
        model.addAttribute("profile", profile);

        return "views/index";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "views/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "views/contact";
    }

    @GetMapping("/services")
    public String services(Model model) {
        return "views/services";
    }


//    @GetMapping("/{category}/{linkStatic}")
//    public String getToursByTourGroup(@PathVariable(value = "linkStatic", required = false) String linkStatic,
//                                      @RequestParam(required = false) Map<String, String> params, Model model, @PathVariable String category) {
//        TourGroup tourGroup = tourGroupService.getTourGroup(linkStatic);
//        int page = Integer.parseInt(params.getOrDefault("page", "1"));
//        int size = Integer.parseInt(params.getOrDefault("size", "4"));
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        List<Tour> tours = tourGroup.getTours();
//
//        model.addAttribute("tourGroup", tourGroup);
//        model.addAttribute("tours", tours);
//
//        return "views/tour/tourGroup";
//    }

    @GetMapping("/{categoryLink}")
    public String getTours(@PathVariable(value = "categoryLink", required = true) String categoryLink,
                           @RequestParam(required = false) Map<String, String> params, Model model) {
        Category category = categoryService.getCategory(categoryLink);
//        TourGroup tourGroup = tourGroupService.getTourGroup("");

        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        int size = Integer.parseInt(params.getOrDefault("size", "4"));

        Pageable pageable = PageRequest.of(page, size);

        int totalPages, totalItems;
        Page<Tour> tourPage;
        List<Tour> tours;
        String name, description;
        tourPage = tourService.getTourPageByCategory(categoryLink, pageable);
        name = category.getName();
        description = category.getDescription();
//        if (tourGroupLink == null) {
//            tourPage = tourService.getTourPage(category, pageable);
//            name = category.getName();
//            description = category.getDescription();
//        } else {
//            tourGroup = tourGroupService.getTourGroup(tourGroupLink);
//            tourPage = tourService.getTourPage(tourGroup, pageable);
//            name = tourGroup.getName();
//            description = tourGroup.getDescription();
//        }

        tours = tourPage.getContent();
        totalPages = tourPage.getTotalPages();
        totalItems = Math.toIntExact(tourPage.getTotalElements());

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("tours", tours);
        model.addAttribute("title", name);
        model.addAttribute("name", name);
        model.addAttribute("description", description);

        return "views/tour/tourGroup";
    }

//
//    @GetMapping("/{categoryLink}")
//    public String getToursByCategory(@PathVariable("categoryLink") String categoryLink,
//                                     @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "6") int size, Model model) {
//        Category category = categoryService.getCategory(categoryLink);
//        Set<TourGroup> tourGroups = category.getTourGroups();
////        List<TourGroup> tourGroups = tourGroupService.getTourGroupsByCategory(categoryLink);
//        tourGroups.forEach(System.out::println);
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Tour> tourPage = tourService.getTourPageByTourGroup(tourGroups, pageable);
//
//        List<Tour> tours = tourPage.getContent();
//        int totalPages = tourPage.getTotalPages();
//        Integer totalItems = Math.toIntExact(tourPage.getTotalElements());
//
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalItems", totalItems);
//        model.addAttribute("tours", tours);
//
//        return "views/tour/tourGroup";
//    }


    /**
     * Xem chương trình tour
     *
     * @param id    mã tour
     * @param model
     * @return views/tour/details.html
     */
    @GetMapping("/tours/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Tour tour = tourService.getById(id);
        Set<TourSchedule> tourSchedules = tour.getTourSchedules();
        TourGroup tourGroup = tour.getTourGroup();
//        List<Tour> tours = tourService.getTours(tourGroup);

        model.addAttribute("tour", tour);
        model.addAttribute("tourSchedules", tourSchedules);
        model.addAttribute("tourGroup", tourGroup);
//        model.addAttribute("getToursByTourGroup", );

        return "views/tour/details";
    }

    /**
     * Tìm kiếm tour theo nhiều tiêu chí
     *
     * @param params
     * @param searchTourDTO form tìm kiếm
     * @param model
     * @return views/tour/list.html
     */
    @GetMapping("/tours")
    public String searchTours(@RequestParam(required = false) Map<String, String> params,
                              @ModelAttribute("searchTourDTO") SearchTourDTO searchTourDTO, Model model) {
        int page, size, totalPages, totalItems;
        Page<Tour> tourPage;
        List<Tour> tours;
        List<Integer> pageNumbers;
        Pageable pageable;

        page = Integer.parseInt(params.getOrDefault("page", "1"));
        size = Integer.parseInt(params.getOrDefault("size", "6"));
        pageable = PageRequest.of(page - 1, size);

//
//        searchTourDTO.setDeparturePlace(params.get("departurePlace"));
        searchTourDTO.setRangeDate(params.get("daterange"));
        searchTourDTO.setRangePrice(params.get("rangePrice"));
//        searchTourDTO.setNumberPeople(Integer.valueOf(params.get("numberPeople")));
        System.out.println(searchTourDTO);

        if (searchTourDTO.getRangeDate() != null) {
            searchTourDTO.convertDate(searchTourDTO.getRangeDate());
        }
        if (searchTourDTO.getRangePrice() != null) {
            searchTourDTO.convertPrice(searchTourDTO.getRangePrice());
        }
        System.out.println(searchTourDTO);

        tourPage = tourService.getTourPageBySearchTour(searchTourDTO, pageable);

        System.out.println(params.size());

        tours = tourPage.getContent();
        totalPages = tourPage.getTotalPages();
        totalItems = Math.toIntExact(tourPage.getTotalElements());

        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }


        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("tours", tours);
        model.addAttribute("tourPage", tourPage);


        tours.forEach(System.out::println);

        return "views/tour/list";
    }

}
