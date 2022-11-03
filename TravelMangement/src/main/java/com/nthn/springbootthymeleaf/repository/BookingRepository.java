package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {
    @Query("select b from Booking b where b.bookingDate between ?1 and ?2 and b.customer.id = ?3")
    List<Booking> findByBookingDateBetweenAndCustomerId(LocalDateTime fromDate, LocalDateTime toDate, Integer customerId);

    @Query("select b from Booking b where b.tour.id = ?1")
    List<Integer> findByTourId(Integer tourId);

    @Query("select b from Booking b where b.customer.id = ?1 order by b.bookingDate")
    List<Booking> getBookingsByCustomerIdOrderByBookingDate(Integer customerId);

    @Query("select b from Booking b where b.customer.id = ?1")
    Page<Booking> findByCustomerId(Integer id, Pageable pageable);

    @Query("select b from Booking b where b.customer.id=?1 and b.bookingDate between ?2 and ?3 and b.payment=?4")
    Page<Booking> findByCustomerId(Integer id, LocalDate fromDate, LocalDate toDate, boolean payment, Pageable pageable);

    @Query("select b from Booking b where b.customer.id=?1 and b.bookingDate between ?2 and ?3")
    Page<Booking> findByCustomerId(Integer id, LocalDate fromDate, LocalDate toDate, Pageable pageable);

    @Query("select month(b.bookingDate), sum(b.total) from Booking b where b.customer.id=?1 and month(b.bookingDate) between ?2 and ?3 group by b.bookingDate order by b.bookingDate")
    List<Object[]> sumBookingTotalByCustomerId(Integer customerId, Integer fromMonth, Integer toMonth);

    @Query("select b.bookingDate, sum(b.total) from Booking b where b.customer.id=?1 and month(b.bookingDate) =?2 and year(b.bookingDate)=?3 group by b.bookingDate order by b.bookingDate")
    List<Object[]> sumBookingTotalInMonthByCustomerId(Integer customerId, Integer month, Integer year);

    @Query("select b.bookingDate, sum(b.total) from Booking b where b.customer.id = ?1 and b.bookingDate between ?2 and ?3 group by b.bookingDate order by b.bookingDate")
    List<Object[]> sumTotalBookingByCustomerId(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);


    @Query("select count(b), sum(b.total) from Booking b where month(b.bookingDate)=?1 and year(b.bookingDate)=?2 and b.payment=true")
    List<Object[]> getRevenueMonthly(int month, int year);

    @Query(" select count(b), sum(b.total) from Booking b where year(b.bookingDate)=?1 and b.payment=true")
    List<Object[]> getRevenueAnnual(int year);

    @Query("select sum(d.quantity) from Booking b, BookingDetail d where b.tour.id = ?1 and d.booking.id=b.id")
    long countBookingByTourId(int tourId);


}