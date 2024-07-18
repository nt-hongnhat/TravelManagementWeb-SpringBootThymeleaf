package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,
		JpaSpecificationExecutor<Category> {
	
	@Query("select c from Category c where upper(c.name) like upper(concat('%', ?1, '%'))")
	List<Category> findAllByNameContainingIgnoreCase(String keyword);
	
	@Query("select c from Category c where c.linkStatic = ?1")
	Category getCategoryByLinkStatic(String linkStatic);
	
	@Query("select (count(c) > 0) from Category c where c.name like ?1 or c.linkStatic like ?2")
	boolean existsByNameOrLinkStatic(String name, String linkStatic);
	
	@Query("select c from Category c where c.name like ?1 or c.linkStatic like ?2")
	Category getCategoryByNameOrLinkStatic(String name, String linkStatic);
	
	@Query("select c from Category c where c.name = ?1")
	Category findByName(String name);
	
	@Query("select c from Category c where c.linkStatic = ?1")
	Category findByLinkStatic(String linkStatic);
}