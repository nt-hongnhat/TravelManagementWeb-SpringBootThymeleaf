package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Integer>,
		JpaSpecificationExecutor<TourSchedule> {
	
	@Transactional
	@Modifying
	@Query("update TourSchedule t set t.itinerary = ?1, t.description = ?2 where t.id = ?3")
	int update(String itinerary, String description, Integer id);
}