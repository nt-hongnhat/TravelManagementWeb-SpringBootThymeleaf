package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

}