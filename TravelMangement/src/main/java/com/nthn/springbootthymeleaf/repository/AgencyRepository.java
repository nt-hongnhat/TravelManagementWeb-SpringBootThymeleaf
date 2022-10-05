package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer>, JpaSpecificationExecutor<Agency> {

}