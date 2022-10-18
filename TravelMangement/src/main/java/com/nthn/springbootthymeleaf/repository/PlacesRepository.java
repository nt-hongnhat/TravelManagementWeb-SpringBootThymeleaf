package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlacesRepository extends JpaRepository<Places, Integer>, JpaSpecificationExecutor<Places> {

}