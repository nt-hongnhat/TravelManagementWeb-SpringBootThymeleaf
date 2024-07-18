package com.nthn.springbootthymeleaf.config.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessHandler
    implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

  /**
   * Logout handler method
   *
   * @param request the request object that will be used to log out the user
   * @param response the response object that will be used to log out the user
   * @param authentication the authentication object that will be used to authenticate the user
   * @throws IOException if an I/O error occurs while logging out the user
   */
  @Override
  public void onLogoutSuccess(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      Authentication authentication)
      throws IOException {
    request.getSession().removeAttribute(Key.CURRENT_USER);
    response.sendRedirect(request.getContextPath() + Url.LOGOUT);
  }
}
