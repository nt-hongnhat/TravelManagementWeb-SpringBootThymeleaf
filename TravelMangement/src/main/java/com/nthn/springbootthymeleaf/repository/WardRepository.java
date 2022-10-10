package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface WardRepository extends JpaRepository<Ward, Integer>, JpaSpecificationExecutor<Ward> {
    @Query("select w from Ward w where upper(w.name) like upper(concat('%', ?1, '%')) order by w.name")
    List<Ward> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);
}