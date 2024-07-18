package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.TourTicket;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TourTicketRepository extends JpaRepository<TourTicket, Integer>,
		JpaSpecificationExecutor<TourTicket> {
	
	@Query("select count(t) from TourTicket t where t.tour.id = ?1")
	long countByTourId(Integer tourId);
	
	@Query("select sum(b.quantity), t.id FROM BookingDetail b,  TourTicket t where b.tourTicket.id=t.id and t.tour.id=?1 group by t.id")
	List<Object> sumByTour(Integer tourId);
	
	@Query("select t from TourTicket t where t.tour.id = ?1")
	List<TourTicket> findByTourId(Integer tourId);
	
	@Transactional
	@Modifying
	@Query("update TourTicket t set t.unitPrice = ?1, t.maxSlot = ?2 where t.id = ?3")
	void update(BigDecimal unitPrice, Integer maxSlot, Integer id);
	
	
}