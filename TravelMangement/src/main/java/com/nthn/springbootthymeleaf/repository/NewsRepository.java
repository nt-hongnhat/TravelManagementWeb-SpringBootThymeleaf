package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.News;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

    @Override
    @NotNull
    Page<News> findAll(@NotNull Pageable pageable);

    @Query("select n from News n where n.tourGroup.id = ?1")
    Page<News> findAllByTourGroupId(Integer tourGroupId, Pageable pageable);

    
}