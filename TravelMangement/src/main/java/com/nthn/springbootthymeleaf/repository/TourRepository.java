package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {

    @Query("select t from Tour t where t.tourGroup.id = ?1")
    List<Tour> findByTourGroupId(Integer id);
    
    @Query("select t from Tour t where t.duration = ?1")
    List<Tour> findByDuration(String duration);
}