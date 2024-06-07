package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService  extends UserDetailsService {
    Account getAccountById(int id);
    List<Account> getAllAccounts();
    void saveAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(int id);
    Account getAccountByEmail(String email);
    Account getAccountByUsername(String username);
}
