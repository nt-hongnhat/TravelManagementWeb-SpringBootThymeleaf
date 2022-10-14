package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Tour;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {
    @Query("select t from Tour t where t.category.id = ?1")
    Page<Tour> findByCategoryId(Integer categoryId, Pageable pageable);
}