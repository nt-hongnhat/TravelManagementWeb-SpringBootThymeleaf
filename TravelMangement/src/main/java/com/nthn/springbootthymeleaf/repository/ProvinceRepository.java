package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProvinceRepository extends JpaRepository<Province, Integer>, JpaSpecificationExecutor<Province> {

    @Query("select p from Province p where upper(p.name) like upper(concat('%', ?1, '%')) order by p.name")
    List<Province> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);
}