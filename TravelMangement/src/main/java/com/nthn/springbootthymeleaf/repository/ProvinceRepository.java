package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface ProvinceRepository extends JpaRepository<Province, String>, JpaSpecificationExecutor<Province> {

}