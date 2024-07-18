package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService extends UserDetailsService {
    Account create(Account account);

    // Register an account with permission customer
    Account register(Account account);

    boolean matchPassword(String confirm, String password);

    Account getAccount(Integer id);

    Account getAccountByUsername(String username);

    Account getAccountByEmail(String email);

    List<Account> getAccounts(String keyword);

    Account update(Integer id, Account account);

    boolean delete(Integer id);

    Account getByResetPasswordToken(String token);

    void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;

    void updatePassword(Account account, String password);
}
