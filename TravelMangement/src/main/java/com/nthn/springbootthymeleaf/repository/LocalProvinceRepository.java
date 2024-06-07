package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.LocalProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocalProvinceRepository extends JpaRepository<LocalProvince, Integer>, JpaSpecificationExecutor<LocalProvince> {
}