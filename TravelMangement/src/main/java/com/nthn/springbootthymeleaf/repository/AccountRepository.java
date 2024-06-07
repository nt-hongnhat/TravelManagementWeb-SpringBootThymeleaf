package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Account findByEmail(String email);
    Account findByUsername(String username);
    List<Account> findByUsernameContaining(String username);

    
}