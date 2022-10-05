package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface DistrictRepository extends JpaRepository<District, String>, JpaSpecificationExecutor<District> {

}