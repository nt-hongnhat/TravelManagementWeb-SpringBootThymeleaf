package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Surcharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SurchargeRepository extends JpaRepository<Surcharge, Integer>, JpaSpecificationExecutor<Surcharge> {
}