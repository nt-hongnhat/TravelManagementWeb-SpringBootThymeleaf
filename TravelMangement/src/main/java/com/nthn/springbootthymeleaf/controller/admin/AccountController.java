package com.nthn.springbootthymeleaf.controller.admin;

import static com.cloudinary.provisioning.Account.ACCOUNTS;
import static com.nthn.springbootthymeleaf.constants.EndpointConstants.DASHBOARD;
import static com.nthn.springbootthymeleaf.constants.ModelViewConstants.AttributeName.CURRENT_USER;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.CloudinaryService;
import com.nthn.springbootthymeleaf.service.PermissionService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Validated
@Controller
@RequestMapping(DASHBOARD + ACCOUNTS)
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	private final CloudinaryService cloudinaryService;
	
	private final PermissionService permissionService;
	
	@ModelAttribute
	public void commonAttribute(@org.jetbrains.annotations.NotNull Model model,
			@org.jetbrains.annotations.NotNull HttpSession httpSession) {
		
		User currentUser = (User) httpSession.getAttribute(CURRENT_USER);
		Account account = accountService.getAccountByUsername(currentUser.getUsername());
		model.addAttribute("permissions", permissionService.getAllPermission());
		model.addAttribute("currentUser", account);
		model.addAttribute("avatar", account.getPhotosImagePath());
	}
	
	@GetMapping()
	public String index(Model model) {
		
		model.addAttribute("accounts", accountService.getAccounts(""));
		return "views/admin/account/list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("account", new Account());
		model.addAttribute("permissions", permissionService.getAllPermission());
		return "views/admin/account/create";
	}
	
	@GetMapping("/{id}")
	public String details(@Validated @NotNull @PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("account", accountService.getAccount(id));
		return "views/admin/account/create";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@Valid @NotNull @PathVariable("id") Integer id) {
		
		accountService.delete(id);
		return "redirect:/dashboard/accounts";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("image") MultipartFile multipartFile,
			@Validated @ModelAttribute("account") Account account, Model model,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest) throws IOException {
		
		model.addAttribute("account", account);
		String path = httpServletRequest.getServletPath().replace("save", "");
		path += account.getId() == null ? "create" : account.getId();
		if (result.hasErrors()) {
			model.addAttribute("permissions", permissionService.getAllPermission());
			redirectAttributes.addFlashAttribute("errorMessage", result.getAllErrors().toString());
			return "redirect:" + path + "?error";
		}
		try {
			if (multipartFile != null) {
				Map map = cloudinaryService.upload(multipartFile);
				account.setAvatarUrl(map.get("url").toString());
			} else {
				account.setAvatarUrl(account.getAvatarUrl());
			}
			account = account.getId() == null ? accountService.create(account)
					: accountService.update(account.getId(), account);
		} catch (Exception exception) {
			model.addAttribute("permissions", permissionService.getAllPermission());
			model.addAttribute("errorMessage", "Lỗi" + exception.getMessage());
			return "redirect:" + path + "?error";
		}
		
		redirectAttributes.addFlashAttribute("successMessage", "Tài khoản cập nhật thành công!");
		redirectAttributes.addFlashAttribute("account", account);
		return "redirect:" + path + "?success";
	}
	
}
