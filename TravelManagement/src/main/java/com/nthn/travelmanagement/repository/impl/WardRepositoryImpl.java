package com.nthn.travelmanagement.repository.impl;

import com.nthn.travelmanagement.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WardRepositoryImpl extends JpaRepository<Ward, String>, JpaSpecificationExecutor<Ward> {

}