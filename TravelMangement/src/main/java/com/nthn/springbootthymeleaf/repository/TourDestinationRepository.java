package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.TourDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TourDestinationRepository extends JpaRepository<TourDestination, Integer>, JpaSpecificationExecutor<TourDestination> {

}