package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DistrictRepository extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District> {
    @Query("select d from District d where upper(d.name) like upper(concat('%', ?1, '%')) order by d.name")
    List<District> findAllByNameContainingIgnoreCaseOrderByNameAsc(String keyword);

}