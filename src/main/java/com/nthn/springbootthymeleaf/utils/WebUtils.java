package com.nthn.springbootthymeleaf.utils;

import com.nthn.springbootthymeleaf.constants.FormatConstants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUtils {
  public static String toString(User user) {
    final StringBuilder builder = new StringBuilder();

    builder.append("UserName:").append(user.getUsername());

    final Collection<GrantedAuthority> authorities = user.getAuthorities();
    if (ObjectUtils.isEmpty(authorities)) {
      builder.append(" (");
      final AtomicBoolean first = new AtomicBoolean(true);
      authorities.forEach(
          grantedAuthority -> {
            if (first.get()) {
              builder.append(grantedAuthority.getAuthority());
              first.set(false);
            } else {
              builder.append(", ").append(grantedAuthority.getAuthority());
            }
          });
      builder.append(")");
    }
    return builder.toString();
  }

  public static @NotNull LocalDate getLocalDate(final String valueDate) {

    return LocalDate.parse(valueDate, DateTimeFormatter.ofPattern(FormatConstants.YYYY_MM_DD));
  }
}
