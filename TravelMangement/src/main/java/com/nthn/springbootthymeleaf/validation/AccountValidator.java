package com.nthn.springbootthymeleaf.validation;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    private final EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private AccountService accountService;
    
    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return clazz == Account.class;
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Account account = (Account) target;

        if (account.getId() == null) {
            Account accountExists = accountService.getAccountByEmail(account.getEmail());
            if (accountExists != null) {
                errors.rejectValue("email", "Duplicate.account.email", "Email đã được sử dụng bởi tài khoản khác");
            }
        }

        if (!errors.hasFieldErrors("username")) {
            Account accountExists = accountService.getAccountByUsername(account.getUsername());
            if (accountExists != null) {
                errors.rejectValue("username", "Duplicate.account.username", "Tên đăng nhập đã bị sử dụng bởi người khác");
            }
        }

    }
}
