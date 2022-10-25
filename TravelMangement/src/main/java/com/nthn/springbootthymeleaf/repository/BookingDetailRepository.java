package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer>, JpaSpecificationExecutor<BookingDetail> {
    @Query("select b from BookingDetail b where b.bookingId = ?1")
    List<BookingDetail> findByBookingId(Integer bookingId);

    @Query("select b from BookingDetail b where b.bookingId in ?1")
    List<BookingDetail> findByBookingIdIn(List<Integer> bookingIds);
}