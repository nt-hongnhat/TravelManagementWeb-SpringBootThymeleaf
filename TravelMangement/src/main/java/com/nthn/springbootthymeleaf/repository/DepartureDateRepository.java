package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.DepartureDate;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureDateRepository extends JpaRepository<DepartureDate, Integer>,
		JpaSpecificationExecutor<DepartureDate> {
	
	@Query("select d from DepartureDate d where d.tour.id = ?1 and d.date = ?2")
	DepartureDate findByTourIdAndDate(Integer id, LocalDate date);
}