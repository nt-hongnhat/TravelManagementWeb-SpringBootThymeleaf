package com.nthn.springbootthymeleaf.controller.auth;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.utils.WebUtils;
import com.nthn.springbootthymeleaf.validator.AccountValidator;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final AccountService accountService;
	
	private final AccountValidator accountValidator;
	
	@InitBinder
	protected void initBinder(@NotNull WebDataBinder webDataBinder) {
		final Object target = webDataBinder.getTarget();
		if (Objects.isNull(target)) {
			return;
		}
		
		log.info("Target: {}", target);
		
		if (Objects.equals(target.getClass(), Account.class)) {
			webDataBinder.setValidator(accountValidator);
		}
	}
	
	@GetMapping(Url.LOGIN)
	public String login() {
		return "views/auth/login";
	}
	
	@GetMapping(Url.REGISTER)
	public String register(Model model) {
		model.addAttribute(Key.ACCOUNT, new Account());
		return "views/auth/register";
	}
	
	@PostMapping(Url.REGISTER)
	public String register(@Valid @ModelAttribute(Key.ACCOUNT) Account accountRegistration,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addAllAttributes(
					Map.of("usernameErrors", result.getFieldErrors(Key.USERNAME), "emailErrors",
							result.getFieldErrors(Key.EMAIL), Key.ACCOUNT, accountRegistration));
			model.addAttribute(Key.ACCOUNT, accountRegistration);
			return "redirect:/register?error";
		}
		
		try {
			accountService.register(accountRegistration);
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute(Key.ACCOUNT, accountRegistration);
			model.addAttribute(Key.ACCOUNT, accountRegistration);
			log.error("Exception: {}", exception.getMessage());
			return "redirect:/register?error";
		}
		redirectAttributes.addFlashAttribute(Key.SUCCESS, "Đăng ký tài khoản thành công!");
		return "redirect:/register?success";
	}
	
	
	@PostMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (Objects.nonNull(auth)) {
			// TODO: check auth
			// new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	
	@GetMapping("/403")
	public String accessDenied(Model model, Principal principal) {
		
		if (principal != null) {
			User loginUser = (User) ((Authentication) principal).getPrincipal();
			
			String userInfo = WebUtils.toString(loginUser);
			
			model.addAttribute("userInfo", userInfo);
			System.out.println(userInfo);
			String message = "Chào " + principal.getName() + "! Bạn không có quyền truy cập trang này!";
			model.addAttribute("message", message);
		}
		return "views/403";
	}
}
