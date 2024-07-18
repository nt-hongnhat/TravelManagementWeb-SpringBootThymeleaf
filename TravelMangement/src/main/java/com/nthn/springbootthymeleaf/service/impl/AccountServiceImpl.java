package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.repository.AccountRepository;
import com.nthn.springbootthymeleaf.repository.PermissionRepository;
import com.nthn.springbootthymeleaf.service.AccountService;
import com.nthn.springbootthymeleaf.service.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public Account create(@NotNull Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setCreateTime(LocalDateTime.now());
        return accountRepository.save(account);
    }
    
    @Override
    // Register an account with permission customer
    public Account register(@NotNull Account account) {
        String password = account.getPassword();
        account.setPassword(this.bCryptPasswordEncoder.encode(password));
        account.setPermission(permissionRepository.findByName("CUSTOMER"));
        account.setCreateTime(LocalDateTime.now());
        return accountRepository.save(account);
    }
    
    @Override
    public boolean matchPassword(String confirm, String password) {
        confirm = bCryptPasswordEncoder.encode(confirm);
        return bCryptPasswordEncoder.matches(confirm, password);
    }
    
    @Override
    public Account getAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }
    
    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
    
    @Override
    public List<Account> getAccounts(String keyword) {
        List<Account> accounts;
        if (keyword == null) {
            
            accounts = accountRepository.findAll().stream().filter(Account::getActive).collect(Collectors.toList());
            return accounts;
            
        } else return accountRepository.findAllByUsernameContaining(keyword);
    }
    
    @Override
    public Account update(Integer id, @NotNull Account account) {
        Account original = this.getAccount(id);
        System.out.println("original: " + original);
        BeanUtils.copyProperties(account, original, "password", "createTime", "permission");
        original = accountRepository.save(original);
        System.out.println("updated: " + original);
        return original;
    }
    
    @Override
    public boolean delete(Integer id) {
        Account account = getAccount(id);
        account.setActive(false);
        return update(id, account) != null;
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
    
    @Override
    public Account getByResetPasswordToken(String token) {
        return accountRepository.findByResetToken(token);
    }
    
    @Override
    public void updateResetPasswordToken(String token, String email) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            account.setResetToken(token);
            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException("Could not find any customer with the email " + email);
        }
    }
    
    @Override
    public void updatePassword(@NotNull Account account, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        
        account.setResetToken(null);
        accountRepository.save(account);
    }
}
