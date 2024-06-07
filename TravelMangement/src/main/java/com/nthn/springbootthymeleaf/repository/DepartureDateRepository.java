package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.DepartureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartureDateRepository extends JpaRepository<DepartureDate, Integer>, JpaSpecificationExecutor<DepartureDate> {
}