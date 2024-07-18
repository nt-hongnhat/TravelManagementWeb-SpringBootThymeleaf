package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>,
		JpaSpecificationExecutor<Customer> {
	
	Customer findById(int id);
	
	@Query("select c from Customer c where c.phone=?1")
	Customer findByPhone(String phone);
	
	@Query("select count(c) from Customer c inner join Booking b where c.id=b.customer.id ")
	long countAllByBookingsIn();
	
	@Query("select c from Customer c where c.account.id = ?1")
	Customer findByAccountId(Integer id);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.firstname = ?1, c.lastname = ?2 where c.id = ?3")
	void update(String firstname, String lastname, Integer id);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.firstname = ?1, c.lastname = ?2, c.gender = ?3, c.account = ?4 where c.id = ?5")
	int updateFirstnameAndLastnameAndGenderAndAccountById(String firstname, String lastname,
			String gender, Account account, Integer id);
}