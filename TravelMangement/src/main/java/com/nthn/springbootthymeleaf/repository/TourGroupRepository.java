package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.TourGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourGroupRepository extends JpaRepository<TourGroup, Integer>, JpaSpecificationExecutor<TourGroup> {
    @Query("select t from TourGroup t where t.linkStatic = ?1")
    TourGroup findByLinkStatic(String linkStatic);

    @Query("select t from TourGroup t where t.category.linkStatic = ?1")
    List<TourGroup> getByCategoryLinkStatic(String linkStatic);
}