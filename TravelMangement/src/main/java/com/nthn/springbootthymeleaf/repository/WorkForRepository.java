package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.WorkFor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface WorkForRepository extends JpaRepository<WorkFor, Integer>, JpaSpecificationExecutor<WorkFor> {

}