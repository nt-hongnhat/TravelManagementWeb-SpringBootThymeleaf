package com.nthn.springbootthymeleaf.config.handlers;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AccountService accountService;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        User user = (User) authentication.getPrincipal();
        Account account = accountService.getAccountByUsername(user.getUsername());
        account.setLastLogin(LocalDateTime.now());
        accountService.update(account.getId(), account);
        request.getSession().setAttribute("currentUser", user);

        authorities.forEach(grantedAuthority -> {
            if (grantedAuthority.getAuthority().equals("ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/dashboard");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (grantedAuthority.getAuthority().equals("CUSTOMER")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                try {
                    redirectStrategy.sendRedirect(request, response, "/");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}