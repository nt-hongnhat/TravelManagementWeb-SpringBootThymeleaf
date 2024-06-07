package com.nthn.springbootthymeleaf.controller.guest;

import com.nthn.springbootthymeleaf.DTO.SearchTourDTO;
import com.nthn.springbootthymeleaf.model.Category;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.GroupService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute
    public void commonAttributes(Model model, HttpSession httpSession) {
        LocalDate now = LocalDate.now();
        List<Category> categories;
        categories = categoryService.findAll();
        SearchTourDTO searchTourDTO = new SearchTourDTO();

//        List<Group> groups = groupService.getAllGroups();

        model.addAttribute("now", now);
        model.addAttribute("categories", categories);
//        model.addAttribute("groups", groups);
//        model.addAttribute("provinces", provinces);
//        model.addAttribute("feedbacks", feedbacks);
//        model.addAttribute("places", places);
        model.addAttribute("searchTourDTO", searchTourDTO);
        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));

    }

    @GetMapping("")
    public String index(Model model, Principal principal, HttpSession httpSession) {


        String profile = null;
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            profile = WebUtils.toString(user);
        }

//        // Đếm số chuyến du lịch
//        int countTours = tourService.getTours().size();
//        // Đếm số lượng khách du lịch
//        int countTourist = customerService.getCustomers().size();
//        // Đếm số bài viết tin tức
//        int countNews = newsService.getNews().size();
//        // Đếm số địa điểm tham quan
//        int countPlaces = placesService.getPlaces().size();
//
//        model.addAttribute("durations", this.tourService.getDurations());
//        model.addAttribute("departurePlaces", this.tourService.getDeparturePlaces());
//        model.addAttribute("countTours", countTours);
//        model.addAttribute("countTourist", countTourist);
//        model.addAttribute("countPlaces", countPlaces);
//        model.addAttribute("countNews", countNews);

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

//    /**
//     * Hiển thị tour theo danh mục tour
//     *
//     * @param categoryLink
//     * @param params
//     * @param model
//     * @return views/tour/tourGroup.html
//     */
//    @GetMapping("/{categoryLink}")
//    public String getToursByCategory(@PathVariable("categoryLink") String categoryLink,
//                                     @RequestParam(required = false) Map<String, String> params, Model model) {
//        Page<Tour> tourPage;
//        List<Tour> tours;
//        List<Integer> pageNumbers;
//        Pageable pageable;
//
//        int page = Integer.parseInt(params.getOrDefault("page", "1"));
//        int size = Integer.parseInt(params.getOrDefault("size", "4"));
//        pageable = PageRequest.of(page - 1, size);
//
//        Category category = categoryService.getCategory(categoryLink);
//
//        System.out.println(category);
//
//        tourPage = tourService.getTourPageByCategory(categoryLink, pageable);
//        System.out.println(category.getTourGroups().toString());
//        System.out.println(tourPage.getTotalPages());
//
//
//        tours = tourPage.getContent();
//        int totalPages = tourPage.getTotalPages();
//        int totalItems = Math.toIntExact(tourPage.getTotalElements());
//
//        if (totalPages > 0) {
//            pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalItems", totalItems);
//        model.addAttribute("tours", tours);
////        model.addAttribute("tourPage", tourPage);
//        model.addAttribute("category", category);
//        model.addAttribute("name", category.getName());
//        model.addAttribute("description", category.getDescription());
//
//        return "views/tour/tourGroup";
//    }

    /**
     * Hiển thị tour theo nhóm tour
     *
     * @param tourGroupLink
     * @param params
     * @param model
     * @return views/tour/tourGroup.html
     */
//    @GetMapping("/{tourGroupLink}")
//    public String getTours(@PathVariable("tourGroupLink") String tourGroupLink,
//                           @RequestParam(required = false) Map<String, String> params, Model model) {
//        int page, size, totalPages, totalItems;
//        Page<Tour> tourPage;
//        List<Tour> tours;
//        List<Integer> pageNumbers;
//        Pageable pageable;
//
//        page = Integer.parseInt(params.getOrDefault("page", "1"));
//        size = Integer.parseInt(params.getOrDefault("size", "4"));
//        pageable = PageRequest.of(page - 1, size);
//
//        TourGroup tourGroup = tourGroupService.getTourGroup(tourGroupLink);
//        System.out.println(tourGroup);
//
//        tourPage = tourService.getTourPageByTourGroup(tourGroup.getId(), pageable);
//        System.out.println(tourGroup.getTours());
//        System.out.println(tourPage.getTotalPages());
//
//
//        tours = tourPage.getContent();
//        totalPages = tourPage.getTotalPages();
//        totalItems = Math.toIntExact(tourPage.getTotalElements());
//
//        if (totalPages > 0) {
//            pageNumbers = IntStream.rangeClosed(1, totalPages)
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
//        model.addAttribute("tourGroup", tourGroup);
//        model.addAttribute("name", tourGroup.getName());
//        model.addAttribute("description", tourGroup.getDescription());
//
//        return "views/tour/tourGroup";
//    }
//
//
//    /**
//     * Tìm kiếm tour theo nhiều tiêu chí
//     *
//     * @param params
//     * @param searchTourDTO form tìm kiếm
//     * @param model
//     * @return views/tour/list.html
//     */
//    @GetMapping("/tours")
//    public String searchTours(@RequestParam(required = false) Map<String, String> params,
//                              @ModelAttribute("searchTourDTO") SearchTourDTO searchTourDTO, Model model) {
//        int page, size, totalPages, totalItems;
//        Page<Tour> tourPage;
//        List<Tour> tours = new ArrayList<>();
//        List<Integer> pageNumbers;
//        Pageable pageable;
//
//        page = Integer.parseInt(params.getOrDefault("page", "1"));
//        size = Integer.parseInt(params.getOrDefault("size", "6"));
//        pageable = PageRequest.of(page - 1, size);
//
//        searchTourDTO.setRangeDate(params.get("daterange"));
//        searchTourDTO.setRangePrice(params.get("rangePrice"));
//
//        if (searchTourDTO.getRangeDate() != null) {
//            searchTourDTO.convertDate(searchTourDTO.getRangeDate());
//        }
//        if (searchTourDTO.getRangePrice() != null) {
//            searchTourDTO.convertPrice(searchTourDTO.getRangePrice());
//        }
//
//        System.out.println(searchTourDTO);
//
//        tourPage = tourService.getTourPageBySearchTour(searchTourDTO, pageable);
//        List<Tour> tourList = tourPage.getContent();
//        tourList.forEach(tour -> {
//            if (tour.getAvailableSlot() >= searchTourDTO.getNumberPeople()) {
//                tours.add(tour);
//            }
//        });
//        totalPages = tourPage.getTotalPages();
//        totalItems = Math.toIntExact(tourPage.getTotalElements());
//
//        if (totalPages > 0) {
//            pageNumbers = IntStream.rangeClosed(1, totalPages)
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
//        model.addAttribute("tourPage", tourPage);
//
//        return "views/tour/list";
//    }
//
//    /**
//     * Xem chương trình tour
//     *
//     * @param id    mã tour
//     * @param model
//     * @return views/tour/details.html
//     */
//    @GetMapping("/tours/{id}")
//    public String getTourDetail(@PathVariable("id") Integer id, Model model) {
//        Tour tour = tourService.getById(id);
//        Set<TourSchedule> tourSchedules = tour.getTourSchedules();
//        TourGroup tourGroup = tour.getTourGroup();
////        List<Tour> tours = tourService.getTours(tourGroup);
//
//        model.addAttribute("tour", tour);
//        model.addAttribute("tourSchedules", tourSchedules);
//        model.addAttribute("tourGroup", tourGroup);
////        model.addAttribute("getToursByTourGroup", );
//
//        return "views/tour/details";
//    }
//
//    // Hiển thị trang tin tức
//    // GET: /news
//    @GetMapping("/news")
//    public String getNews(@RequestParam(required = false) Map<String, String> params, Model model) {
//        int page, size, totalPages, totalItems;
//        Page<News> newsPage;
//        List<News> news;
//        List<Integer> pageNumbers;
//        Pageable pageable;
//
//        page = Integer.parseInt(params.getOrDefault("page", "1"));
//        size = Integer.parseInt(params.getOrDefault("size", "4"));
//        pageable = PageRequest.of(page - 1, size);
//
//
//        newsPage = newsService.getNewsPage(pageable);
//        System.out.println(newsPage.getTotalPages());
//
//
//        news = newsPage.getContent();
//        totalPages = newsPage.getTotalPages();
//        totalItems = Math.toIntExact(newsPage.getTotalElements());
//
//        if (totalPages > 0) {
//            pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//        News news1 = news.stream().max((o1, o2) -> (o1.getViews() - o2.getViews())).get();
////        List<News> newsList= new ArrayList<>();
////        news.stream().filter(news2 -> news2.);
//        model.addAttribute("topNews", news1);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalItems", totalItems);
//        model.addAttribute("newsList", news);
//        System.out.println(news);
//        news.forEach(System.out::println);
//        return "views/news/list";
//    }
//
//    // Xem chi tiết tin tức
//    // GET: /news/{id}
//    @GetMapping("/news/{id}")
//    public String detail(@PathVariable("id") Integer id, Model model, Principal principal) {
//        News news = newsService.getById(id);
//        System.out.println(news);
//
//        List<Comment> comments = news.getComments();
//        if (principal != null) {
//            model.addAttribute("account", accountService.getAccountByUsername(principal.getName()));
//            System.out.println(accountService.getAccountByUsername(principal.getName()));
//        }
//
//        System.out.println(news.getViews());
////        newsService.update(news.getId(), news);
//        news = newsService.getById(id);
//        System.out.println(news);
//
//        model.addAttribute("news", news);
//        model.addAttribute("comments", comments);
////        model.addAttribute("endpoint", "http://localhost:8080/Travel/api/news/comments");
//        System.out.println(comments);
//
//        return "views/news/details";
//    }
}
