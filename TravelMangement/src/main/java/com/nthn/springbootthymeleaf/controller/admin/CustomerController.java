package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.model.Account;
import com.nthn.springbootthymeleaf.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Validated
@Controller
@RequestMapping("/dashboard/customers")
public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private BookingService bookingService;
//
//    @ModelAttribute
//    public void commonAttribute(Model model, HttpSession httpSession) {
//        User currentUser = (User) httpSession.getAttribute("currentUser");
//        Account account = accountService.getAccountByUsername(currentUser.getUsername());
//        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
////        model.addAttribute("avatar", account.getPhotosImagePath());
//    }
//
//    // Hiển thị danh sách khách hàng
//    // GET: /customer
//    @GetMapping
//    public String index(Model model) {
//        List<Customer> customers = customerService.getCustomers();
//        long countByNationality_0 = customerService.countAllByNationality("Việt Nam");
//        long countByNationality_1 = customerService.countAllByNationality("Nước ngoài");
//        model.addAttribute("customers", customers);
//        model.addAttribute("countByNationality_VN", countByNationality_0);
//        model.addAttribute("countByNationality_NN", countByNationality_1);
//
//        return "views/admin/customer/list";
//    }
//
//    // Hiển thị giao diện tạo khách hàng
//    // GET: /customer/create
//    @GetMapping("/create")
//    public String create(Model model) {
//        model.addAttribute("newCustomer", new Customer());
//        return "views/admin/customer/create";
//    }
//
//    // Lưu thông tin khách hàng
//    // POST: /customer/create
//    @PostMapping("/create")
//    public String save(@Valid Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi!!! Thử lại");
//            return "redirect:/admin/customers/create?error";
//        }
//        customerService.save(customer);
//        redirectAttributes.addFlashAttribute("success", "Thêm khách hàng thành công!");
//        return "redirect:/admin/customers/create?success";
//    }
//
//
//    // Hiển thị trang hồ sơ khách hàng
//    // GET: /customers/{id}/edit
//    @GetMapping("/{id}/edit")
//    public String edit(@Valid @NotNull @PathVariable("id") Integer id, Model model) {
//        Customer customer = customerService.getById(id);
//        Account account = customer.getAccount();
//        model.addAttribute("customer", customer);
//        model.addAttribute("account", account);
//        return "views/admin/customer/edit";
//    }
//
//    // Chỉnh sửa hồ sơ khách hàng
//    // POST: /customers/{id}/edit
//    @PostMapping("/{id}/edit")
//    public String update(@PathVariable("id") Integer id, @ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        customerService.update(customer.getId(), customer);
//        redirectAttributes.addFlashAttribute("success", "Cập nhật hồ sơ khách hàng thành công!");
//        return "redirect:/admin/customers/{id}?success";
//    }
//
//
//    @GetMapping("/{id}")
//    public String details(@PathVariable("id") Integer id, Model model) {
//        Customer customer = customerService.getById(id);
//        List<Booking> bookings = bookingService.getBookingsByCustomer(id);
//        LocalDateTime now = LocalDateTime.now();
//        int month = now.getMonthValue();
//        int year = now.getYear();
//        List<Object[]> stats = bookingService.sumBookingTotalInMonthByCustomerId(id, month, year);
//
//
//        Map<String, BigDecimal> totals = new LinkedHashMap<>();
//        for (int i = 0; i < now.getDayOfMonth(); i++) {
//            int x = i;
//            stats.forEach(stat -> {
//                if ((Integer) stat[0] == x) {
//                    totals.put(LocalDate.of(year, month, (Integer) stat[0]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), (BigDecimal) stat[1]);
//                } else {
//                    totals.put(LocalDate.of(year, month, x + 1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), BigDecimal.valueOf(0));
//                }
//            });
//        }
//
//
//        model.addAttribute("customer", customer);
//        model.addAttribute("bookings", bookings);
//        model.addAttribute("stats", stats);
//        model.addAttribute("time", month + "/" + year);
//        stats.forEach(o -> {
//            Arrays.stream(o).toList().forEach(System.out::println);
//        });
//
//        model.addAttribute("totals", totals);
//        model.addAttribute("keySet", totals.keySet());
//        model.addAttribute("values", totals.values());
//
//
//        return "views/admin/customer/details";
//    }


}
