package com.nthn.travelmanagement.repository.impl;

import com.nthn.travelmanagement.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProvinceRepositoryImpl extends JpaRepository<Province, String>, JpaSpecificationExecutor<Province> {

}