package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    @Query("select a from Account a where a.username = ?1")
    Account findByUsername(String username);

    @Query("select a from Account a where a.firstName like concat('%', ?1, '%') and a.lastName like concat('%', ?2, '%')")
    List<Account> findAllByFirstNameContainingAndLastNameContaining(String keyword);

    @Query("select a from Account a where a.username like concat('%', ?1, '%')")
    List<Account> findAllByUsernameContaining(String keyword);
}