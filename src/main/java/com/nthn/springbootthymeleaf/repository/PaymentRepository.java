package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>,
		JpaSpecificationExecutor<Payment> {
	
	@Query("select p from Payment p where p.method = ?1")
	Payment findByMethod(String method);
}