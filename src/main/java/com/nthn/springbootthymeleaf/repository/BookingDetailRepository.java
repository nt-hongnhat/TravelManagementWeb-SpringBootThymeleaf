package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.BookingDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer>,
		JpaSpecificationExecutor<BookingDetail> {
	
	@Query("select b from BookingDetail b where b.booking.id = ?1")
	List<BookingDetail> findByBookingId(Integer bookingId);
	
	@Query("select b from BookingDetail b where b.booking.id in ?1")
	List<BookingDetail> findByBookingIdIn(List<Integer> bookingIds);
}