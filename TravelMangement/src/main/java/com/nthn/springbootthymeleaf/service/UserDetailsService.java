package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.repository.AccountRepository;
import com.nthn.springbootthymeleaf.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("Account " + username + " was not found findByUsername the database");
        }
        System.out.println("Found User: " + account);

        List<String> permissionNames = new ArrayList<>();
        permissionRepository.findAll().forEach(permission -> permissionNames.add(permission.getName()));

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        for (String name : permissionNames) {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(name);
            grantList.add(authority);
        }

        return new User(account.getUsername(), //
                account.getPassword(), grantList);
    }
}
