package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.pojo.Customer;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/{locale:en|vi}/admin/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách khách hàng
    // GET: /customer
    @GetMapping("")
    public String index(@RequestParam(required = false) Map<String, String> params, Model model) {
        if (params.isEmpty()) {
            model.addAttribute("customers", customerService.getCustomers());
        } else {
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int size = Integer.parseInt(params.getOrDefault("size", "10"));
            Pageable pageable = PageRequest.of(page, size);
            Page<Customer> customerPage = customerService.getCustomers(pageable);
            model.addAttribute("customers", customerPage.getContent());
        }
        return "views/admin/customerList";
    }

    // Hiển thị giao diện tạo khách hàng
    // GET: /customer/create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "views/customer";
    }

    // Lưu thông tin khách hàng
    // POST: /customer/create
    @PostMapping("/create")
    public String save(@Valid Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "views/customer";
        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("Success", "Saved customer successfully!");
        return "redirect:/";
    }

    // DELETE: /customer/{id}
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        customerService.delete(id);
    }

    // Hiển thị trang chỉnh sửa hồ sơ khách hàng
    // GET: /customer/{id}/edit
    @GetMapping("/{id}/edit")
    public String edit(@Valid @NotNull @PathVariable("id") Integer id,
                       Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "views/customerEdit";
    }

    // Chỉnh sửa hồ sơ khách hàng
    // PUT: /customer/{id}
    @PutMapping("/update")
    public String update(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.update(customer.getId(), customer);
        redirectAttributes.addFlashAttribute("Success", "Modified customer successfully!");
        return "redirect:/customer/";
    }

    // GET: /customer/{id}
    @GetMapping("/{id}")
    public String getById(@Valid @NotNull @PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "views/customerProfile";
    }


}
