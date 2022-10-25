package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourTicket;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/tours")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private SurchargeService surchargeService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TourGroupService tourGroupService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private NewsService newsService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private TourScheduleService tourScheduleService;
    @Autowired
    private PlacesService placesService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        LocalDateTime today = LocalDateTime.now();
        model.addAttribute("today", today);
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("tourGroups", tourGroupService.getTourGroups());
        model.addAttribute("provinces", provinceService.getProvinces());
    }

    @GetMapping
    public String getTours(Model model) {
        List<Tour> tours = tourService.getTours();

        model.addAttribute("tours", tours);

        return "views/admin/tour/list";
    }


    @GetMapping("/create")
    public String create(Model model) {
        List<TourTicket> tourTickets = new ArrayList<>();

        model.addAttribute("newTour", new Tour());
        model.addAttribute("tourTickets", tourTickets);

        return "views/admin/tour/create";
    }

    @PostMapping("/create")
    public String save(@RequestParam("tourImage") MultipartFile multipartFile, @Validated @ModelAttribute("newTour") Tour newTour, Model model, BindingResult result, final RedirectAttributes redirectAttributes) throws IOException {
        System.out.println("New: " + newTour);
        System.out.println(multipartFile.getOriginalFilename());

        model.addAttribute("newTour", newTour);

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error" + result.getAllErrors());
            return "redirect:/admin/tours/create?error";
        }

        Tour tourSaved;

        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            System.out.println(fileName);

            newTour.setImage(fileName);
            tourSaved = tourService.create(newTour);

            String uploadDir = "src/main/resources/static/tour/" + tourSaved.getId();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Không thể đăng tải file: " + fileName);
            }

            System.out.println(tourSaved);

        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + exception.getMessage());
            return "redirect:/admin/tours/create?error";
        }

        redirectAttributes.addFlashAttribute("success", "Tạo tour thành công!");
        return "redirect:/admin/tours/create?success";
    }


    @GetMapping("/{id}/details")
    public String details(Model model, @PathVariable("id") Integer tourId) {
        Tour tour = tourService.getById(tourId);

        model.addAttribute("tour", tour);
        return "views/admin/tour/details";
    }


    @DeleteMapping("/{id}/delete")
    public String deleteTourById(@PathVariable("id") Integer id) {
        tourService.delete(id);
        return "redirect:/admin/tours/";
    }


}
