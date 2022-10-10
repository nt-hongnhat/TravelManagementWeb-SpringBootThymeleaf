package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
    @Query("select c from Category c where upper(c.name) like upper(concat('%', ?1, '%'))")
    List<Category> findAllByNameContainingIgnoreCase(String keyword);
}