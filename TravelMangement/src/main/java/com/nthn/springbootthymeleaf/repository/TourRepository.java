package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {

}