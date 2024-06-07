package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.LocalDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocalDistrictRepository extends JpaRepository<LocalDistrict, Integer>, JpaSpecificationExecutor<LocalDistrict> {
}