package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>,
		JpaSpecificationExecutor<District> {
	
	@Query("select d from District d where upper(d.name) like upper(concat('%', ?1, '%')) order by d.name")
	List<District> findAllByNameContainingIgnoreCaseOrderByNameAsc(String keyword);
	
}