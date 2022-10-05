package com.nthn.travelmanagement.repository.impl;

import com.nthn.travelmanagement.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DistrictRepositoryImpl extends JpaRepository<District, String>, JpaSpecificationExecutor<District> {

}