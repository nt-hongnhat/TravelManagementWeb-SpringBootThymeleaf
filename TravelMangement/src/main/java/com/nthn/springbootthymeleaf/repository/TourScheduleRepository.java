package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Integer>, JpaSpecificationExecutor<TourSchedule> {

}