package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.AccountDTO;
import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.repository.AccountRepository;
import com.nthn.springbootthymeleaf.repository.PermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account create(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        account.setCreateTime(LocalDateTime.now());
        account.setAvatarUrl("");
        return accountRepository.save(account);
    }

    public Account create(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setCreateTime(LocalDateTime.now());
        account.setAvatarUrl("");
        return accountRepository.save(account);
    }

    // Register an account with permission customer
    public Account register(Account account) {
        String password = account.getPassword();
        account.setPassword(this.bCryptPasswordEncoder.encode(password));
        account.setPermission(permissionRepository.findByName("CUSTOMER"));
        account.setCreateTime(LocalDateTime.now());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            System.out.println("Account not found! " + username);
            throw new UsernameNotFoundException("Account " + username + " was not found in the database");
        }
        System.out.println("Found User: " + account);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getPermission().getName()));
        return new User(account.getUsername(), account.getPassword(), grantedAuthorities);
    }
}
