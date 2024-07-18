package com.nthn.springbootthymeleaf.controller.admin;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName;
import com.nthn.springbootthymeleaf.entity.Category;
import com.nthn.springbootthymeleaf.entity.TourGroup;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.CategoryService;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import com.nthn.springbootthymeleaf.service.TourGroupService;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(EndpointConstants.DASHBOARD + EndpointConstants.CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {
	
	private final AccountService accountService;
	
	private final CategoryService categoryService;
	
	private final ProvinceService provinceService;
	
	private final TourGroupService tourGroupService;
	
	@ModelAttribute
	public void commonAttributes(Model model, HttpSession httpSession) {
		final User currentUser = (User) httpSession.getAttribute(AttributeName.CURRENT_USER);
		model.addAllAttributes(Map.of(AttributeName.CATEGORIES, this.categoryService.getCategories(),
				AttributeName.PROVINCES, this.provinceService.getProvinces(), AttributeName.CURRENT_USER,
				currentUser, AttributeName.AVATAR,
				accountService.getAccountByUsername(currentUser.getUsername()).getPhotosImagePath()));
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute(AttributeName.CATEGORIES, categoryService.getCategories());
		return "views/admin/categories/list";
	}
	
	@GetMapping("/{id}/create")
	public String create(Model model, @PathVariable int id) {
		final Category category = categoryService.getCategoryById(id);
		
		model.addAllAttributes(Map.of(AttributeName.CATEGORY, category, AttributeName.TOUR_GROUP,
				new TourGroup().setCategory(category)));
		
		return "views/admin/categories/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("category") Category category, Model model,
			RedirectAttributes redirectAttributes) {
		if (Objects.nonNull(categoryService.getCategoryByName(category.getName()))) {
			if (Objects.nonNull(categoryService.getByLinkStatus(category.getLinkStatic()))) {
				categoryService.create(category);
				
				return "redirect:/dashboard/categories/" + category.getId();
			} else {
				redirectAttributes.addFlashAttribute(AttributeName.CATEGORY, category);
				redirectAttributes.addFlashAttribute("linkError", "Đường dẫn đã tồn tại.");
				
				return "redirect:/dashboard/categories/create?error";
			}
		}
		
		redirectAttributes.addFlashAttribute(AttributeName.CATEGORY, category);
		redirectAttributes.addFlashAttribute("nameError", "Tên danh mục đã tồn tại.");
		
		return "views/admin/categories/create?error";
	}
	
	@GetMapping(EndpointConstants.PATH_VARIABLE_ID)
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute(AttributeName.CATEGORY, categoryService.getCategoryById(id));
		return "views/admin/categories/details";
	}
}
