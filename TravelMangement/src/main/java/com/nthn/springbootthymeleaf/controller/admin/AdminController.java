package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.service.*;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
//@RolesAllowed({"ADMIN"})
@PreAuthorize("hasRole('ADMIN')")
//@Secured("ADMIN")
public class AdminController {
//    @Autowired
//    private AccountService accountService;
//    @Autowired
//    private PermissionService permissionService;
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private ProvinceService provinceService;
//    @Autowired
//    private TourService tourService;
//    @Autowired
//    private StatisticService statisticService;
//
//    @ModelAttribute
//    public void commonAttributes(Model model, HttpSession httpSession) {
//        LocalDateTime now = LocalDateTime.now();
//        User currentUser = (User) httpSession.getAttribute("currentUser");
//        Account account = accountService.getAccountByUsername(currentUser.getUsername());
//        model.addAttribute("categories", this.categoryService.getCategories(""));
//        model.addAttribute("provinces", this.provinceService.getProvinces(""));
//        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
//        model.addAttribute("avatar", account.getPhotosImagePath());
//        model.addAttribute("revenueMonthly", statisticService.getRevenueMonthly(now.getMonth().getValue(), now.getYear()).get(0));
//        model.addAttribute("now", now);
//        model.addAttribute("revenueAnnual", statisticService.getRevenueAnnual(now.getYear()).get(0));
//    }
//
//    @GetMapping
//    public String index(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//
//        User user = (User) ((Authentication) principal).getPrincipal();
//
//        String profile = WebUtils.toString(user);
//        LocalDateTime now = LocalDateTime.now();
//
//        List<Object[]> objects = statisticService.getRevenueMonthlyByYear(now.getYear());
//        Map<Integer, BigDecimal> chartAreaData = new LinkedHashMap<>();
//        for (int i = 0; i < 12; i++) {
//            BigDecimal total = (BigDecimal) statisticService.getRevenueMonthly(i + 1, now.getYear()).get(0)[1];
//            if (total == null) {
//                total = BigDecimal.valueOf(0);
//            }
//            int month = i + 1;
//            chartAreaData.put(month, total);
//        }
//        chartAreaData.forEach((month, bigDecimal) -> System.out.println("chartDataArea: " + month + "=" + bigDecimal));
//        model.addAttribute("chartAreaData", chartAreaData);
//        model.addAttribute("profile", profile);
//        return "views/admin/index";
//    }

}
