package com.nthn.springbootthymeleaf.controller.auth;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

  public static final String ATTRIBUTE_NAME_ACCOUNT = "account";

  private final AccountService accountService;

  private final AccountValidator accountValidator;

  @GetMapping("/403")
  public String accessDenied(Model model, Principal principal) {

    if (Objects.nonNull(principal)) {
      final User loginUser = (User) ((Authentication) principal).getPrincipal();

      model.addAllAttributes(
          Map.of(
              "userInfo",
              WebUtils.toString(loginUser),
              "message",
              String.format("Access Denied for %s", principal.getName())));
    }
    return "views/403";
  }

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

  @GetMapping(EndpointConstants.LOGIN)
  public String login() {
    return "views/auth/login";
  }

  @PostMapping("/logout")
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.nonNull(auth)) {
      // TODO: check auth
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

  @GetMapping(EndpointConstants.REGISTER)
  public String register(Model model) {
    model.addAttribute(ATTRIBUTE_NAME_ACCOUNT, new Account());
    return "views/auth/register";
  }

  @PostMapping(EndpointConstants.REGISTER)
  public String register(
      @Valid @ModelAttribute Account accountRegistration,
      BindingResult result,
      Model model,
      final RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addAllAttributes(
          Map.of(
              "usernameErrors",
              result.getFieldErrors("username"),
              "emailErrors",
              result.getFieldErrors("email"),
              "account",
              accountRegistration));
      model.addAttribute("account", accountRegistration);
      return "redirect:/register?error";
    }

    try {
      accountService.register(accountRegistration);
    } catch (Exception exception) {
      redirectAttributes.addFlashAttribute("account", accountRegistration);
      model.addAttribute("account", accountRegistration);
      log.error("Exception: {}", exception.getMessage());
      return "redirect:/register?error";
    }
    redirectAttributes.addFlashAttribute("successMessage", "Đăng ký tài khoản thành công!");
    return "redirect:/register?success";
  }
}
