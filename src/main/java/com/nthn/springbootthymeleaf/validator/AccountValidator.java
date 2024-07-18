package com.nthn.springbootthymeleaf.validator;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

  private final AccountService accountService;

  private final EmailValidator emailValidator = EmailValidator.getInstance();

  @Override
  public boolean supports(@NotNull Class<?> clazz) {
    return clazz == Account.class;
  }

  @Override
  public void validate(@NotNull Object target, @NotNull Errors errors) {
    final Account account = (Account) target;

    if (Objects.isNull(account.getId())) {
      final Account accountExists = accountService.getAccountByEmail(account.getEmail());
      if (Objects.nonNull(accountExists)) {
        errors.rejectValue(
            "email", "Duplicate.account.email", "Email đã được sử dụng bởi tài khoản khác");
      }
    }

    if (!errors.hasFieldErrors("username")) {
      final Account accountExists = accountService.getAccountByUsername(account.getUsername());
      if (Objects.nonNull(accountExists)) {
        errors.rejectValue(
            "username", "Duplicate.account.username", "Tên đăng nhập đã bị sử dụng bởi người khác");
      }
    }
  }
}
