package com.nthn.springbootthymeleaf.service;

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
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface AccountService extends UserDetailsService {
    Account create(Account account);

    // Register an account with permission customer
    Account register(Account account);

    boolean matchPassword(String confirm, String password);

    Account getAccount(Integer id);

    Account getAccountByUsername(String username);

    Account getAccountByEmail(String email);

    List<Account> getAccounts(String keyword);

    boolean update(Integer id, Account account);

    boolean delete(Integer id);

    Account getByResetPasswordToken(String token);

    void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;

    void updatePassword(Account account, String password);
}
