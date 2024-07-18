package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.TourDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TourDestinationRepository extends JpaRepository<TourDestination, Integer>,
		JpaSpecificationExecutor<TourDestination> {
	
}