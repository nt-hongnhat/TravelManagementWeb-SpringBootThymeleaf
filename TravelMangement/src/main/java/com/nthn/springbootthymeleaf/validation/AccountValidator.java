package com.nthn.springbootthymeleaf.validation;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    private final EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private AccountService accountService;

    /**
     * Can this {@link Validator} {@link #validate(Object, Errors) validate}
     * instances of the supplied {@code clazz}?
     * <p>This method is <i>typically</i> implemented like so:
     * <pre class="code">return Foo.class.isAssignableFrom(clazz);</pre>
     * (Where {@code Foo} is the class (or superclass) of the actual
     * object instance that is to be {@link #validate(Object, Errors) validated}.)
     *
     * @param clazz the {@link Class} that this {@link Validator} is
     *              being asked if it can {@link #validate(Object, Errors) validate}
     * @return {@code true} if this {@link Validator} can indeed
     * {@link #validate(Object, Errors) validate} instances of the
     * supplied {@code clazz}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Account.class;
    }

    /**
     * Validate the supplied {@code target} object, which must be
     * of a {@link Class} for which the {@link #supports(Class)} method
     * typically has (or would) return {@code true}.
     * <p>The supplied {@link Errors errors} instance can be used to report
     * any resulting validation errors.
     *
     * @param target the object that is to be validated
     * @param errors contextual state about the validation process
     * @see ValidationUtils
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Account account = (Account) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.account.firstName", "Tên không được bỏ trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.account.lastName", "Họ và chữ lót không được bỏ trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.account.email", "Email không được bỏ trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.account.userName", "Tên đăng nhập không được bỏ trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.account.password", "Mật khẩu không được bỏ trống");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.account.confirmPassword", "Xác nhận mật khẩu không được bỏ trống");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.appUserForm.countryCode");

        if (this.emailValidator.isValid(account.getEmail())) {
            // Email không hợp lệ
            errors.rejectValue("email", "Pattern.account.email", "Email không hợp lệ");
        } else if (account.getId() == null) {
            Account accountExists = accountService.getAccountByEmail(account.getEmail());
            if (accountExists != null) {
                // Email đã được sử dụng bởi tài khoản khác
                errors.rejectValue("email", "Duplicate.account.email", "Email đã được sử dụng bởi tài khoản khác");
            }
        }

        if (!errors.hasFieldErrors("username")) {
            Account accountExists = accountService.getAccountByUsername(account.getUsername());
            if (accountExists != null) {
                errors.rejectValue("username", "Duplicate.account.username", "Tên đăng nhập đã bị sử dụng bởi người khác");
            }
        }

        if (!errors.hasErrors()) {
            if (!account.getConfirmPassword().equals(account.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.account.confirmPassword", "Xác nhận mật khẩu không khớp với mật khẩu");
            }
        }

    }
}
