package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
    Category findByName(String name);
    Category findById(int id);
    @Query("select c from Category c")
    @NotNull
    List<Category> findAll();
    List<Category> findByNameContaining(String name);

}