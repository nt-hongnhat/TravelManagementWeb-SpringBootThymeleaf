package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.TourTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourTicketRepository extends JpaRepository<TourTicket, Integer>, JpaSpecificationExecutor<TourTicket> {
    @Query("select count(t) from TourTicket t where t.tour.id = ?1")
    long countByTourId(Integer tourId);

    
}