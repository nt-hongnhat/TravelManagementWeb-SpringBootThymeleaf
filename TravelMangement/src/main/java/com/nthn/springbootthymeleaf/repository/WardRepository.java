package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface WardRepository extends JpaRepository<Ward, String>, JpaSpecificationExecutor<Ward> {

}