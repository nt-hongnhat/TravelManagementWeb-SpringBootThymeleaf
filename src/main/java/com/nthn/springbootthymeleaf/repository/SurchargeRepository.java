package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Surcharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SurchargeRepository extends JpaRepository<Surcharge, Integer>,
		JpaSpecificationExecutor<Surcharge> {
	
}