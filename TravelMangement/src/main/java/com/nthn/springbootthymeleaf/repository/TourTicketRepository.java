package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.TourTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TourTicketRepository extends JpaRepository<TourTicket, Integer>, JpaSpecificationExecutor<TourTicket> {

}