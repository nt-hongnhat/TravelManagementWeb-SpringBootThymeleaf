package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {
}