package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>,
		JpaSpecificationExecutor<Permission> {
	
	@Query("select p from Permission p where p.name = ?1")
	Permission findByName(String name);
	
	
	@Modifying
	@Transactional
	@Query("update Permission p set p.name=?1, p.description=?2 where p.id=?3")
	Permission update(String name, String description, int id);
	
	
}