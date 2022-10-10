package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.AccountDTO;
import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Account create(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setCreateTime(LocalDateTime.now());
        account.setAvatarUrl("");
        return accountRepository.save(account);
    }

    public Account create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setCreateTime(LocalDateTime.now());
        account.setAvatarUrl("");
        return accountRepository.save(account);
    }


    public Account getAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account getAccount(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<Account> getAccounts(String keyword) {
        if (keyword == null) {
            return accountRepository.findAll(Sort.by("username").ascending());
        } else return accountRepository.findAllByUsernameContaining(keyword);
    }

    public boolean update(Integer id, Account account) {
        Account original = this.getAccount(id);
        BeanUtils.copyProperties(account, original);
        accountRepository.save(original);
        return accountRepository.existsById(id);
    }

    public boolean delete(Integer id) {
        accountRepository.deleteById(id);
        return !accountRepository.existsById(id);
    }

    public UserDetails userDetails(String username) {
        List<Account> accounts = this.getAccounts(username);
        if (accounts.isEmpty()) {
            throw new UsernameNotFoundException("Tài khoản không khả dụng!");
        }
        Account account = accounts.get(0);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getPermission().getName()));
        return new User(account.getUsername(), account.getPassword(), grantedAuthorities);
    }

}
