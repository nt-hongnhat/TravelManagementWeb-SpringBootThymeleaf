package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Account;
import java.util.List;
import javax.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer>,
		JpaSpecificationExecutor<Account> {
	
	@Query("select a from Account a where a.username = ?1")
	Account findByUsername(String username);
	
	@Query("select a from Account a where a.firstName like concat('%', ?1, '%') and a.lastName like concat('%', ?2, '%')")
	List<Account> findAllByFirstNameContainingAndLastNameContaining(String keyword);
	
	@Query("select a from Account a where a.username like concat('%', ?1, '%')")
	List<Account> findAllByUsernameContaining(String keyword);
	
	@Query("select a from Account a where a.active = ?1")
	List<Account> findAllByActive(Integer active);
	
	@Query("select a from Account a where a.email = ?1")
	Account findByEmail(@Email String email);
	
	@Query("select a from Account a where a.resetToken = ?1")
	Account findByResetToken(String resetToken);
}