package com.nthn.springbootthymeleaf.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class UrlLocaleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }

        // Lấy ra thông tin Locale từ LocaleResolver
        Locale locale = localeResolver.resolveLocale(request);

        localeResolver.setLocale(request, response, locale);

        return true;
    }

}