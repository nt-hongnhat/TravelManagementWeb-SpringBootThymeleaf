package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Places;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PlacesRepository extends JpaRepository<Places, Integer>, JpaSpecificationExecutor<Places> {
    @Query("select p from Places p where p.district.id = ?1")
    Page<Places> findAllByDistrictId(int district_id, Pageable pageable);
}