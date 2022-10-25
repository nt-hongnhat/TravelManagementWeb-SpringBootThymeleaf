package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {
    @Query("select p from Permission p where p.name = ?1")
    Permission findByName(String name);

}