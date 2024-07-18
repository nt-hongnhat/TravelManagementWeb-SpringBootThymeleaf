package com.nthn.springbootthymeleaf.config.handlers;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private final AccountService accountService;
	
	/**
	 * @param request        the request object that will be used to log in the user
	 * @param response       the response object that will be used to log in the user
	 * @param authentication the authentication object that will be used to login the user
	 */
	@Override
	public void onAuthenticationSuccess(@NotNull HttpServletRequest request,
			HttpServletResponse response,
			@NotNull Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		final User user = (User) authentication.getPrincipal();
		final Account account =
				accountService.getAccountByUsername(user.getUsername()).setLastLogin(LocalDateTime.now());
		
		accountService.update(account.getId(), account);
		request.getSession().setAttribute(Key.CURRENT_USER, user);
		
		authorities.forEach(grantedAuthority -> {
			if (grantedAuthority.getAuthority().equals(Role.ADMIN.getName())) {
				try {
					redirectStrategy.sendRedirect(request, response, Url.DASHBOARD);
					return;
				} catch (Exception exception) {
					log.error(exception.getMessage());
				}
			}
			try {
				redirectStrategy.sendRedirect(request, response, Url.DEFAULT);
			} catch (IOException ioException) {
				log.error(ioException.getMessage());
			}
		});
	}
}