package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.DTO.BookingStatisticsResult;
import com.nthn.springbootthymeleaf.entity.Booking;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookingRepository
    extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {
  @Query("select b from Booking b where b.bookingDate between ?1 and ?2 and b.customer.id = ?3")
  List<Booking> findByBookingDateBetweenAndCustomerId(
      LocalDateTime fromDate, LocalDateTime toDate, Integer customerId);

  @Query("select b from Booking b where b.customer.id = ?1")
  Page<Booking> findByCustomerId(Integer id, Pageable pageable);

  @Query(
      "select b from Booking b where b.customer.id=?1 and b.departureDate.date between ?2 and ?3")
  Page<Booking> findByCustomerId(
      Integer id, LocalDate fromDate, LocalDate toDate, Pageable pageable);

  @Query(
      "select b from Booking b where b.customer.id=?1 and b.departureDate.date between ?2 and ?3 and b.payment=null")
  Page<Booking> findByCustomerIdNoPaid(
      Integer id, LocalDate fromDate, LocalDate toDate, Pageable pageable);

  @Query(
      "select b from Booking b where b.customer.id=?1 and b.departureDate.date between ?2 and ?3 and b"
          + ".payment!=null")
  Page<Booking> findByCustomerIdPaid(
      Integer id, LocalDate fromDate, LocalDate toDate, Pageable pageable);

  @Query("select b from Booking b where b.payment is not null")
  List<Booking> findByPaymentNotNull();

  @Query("select b from Booking b where b.payment is null")
  List<Booking> findByPaymentNull();

  @Query("select b from Booking b where b.customer.id = ?1 order by b.bookingDate")
  List<Object[]> getBookingsByCustomerIdOrderByBookingDate(Integer customerId);

  @Query(
      " select count(b), sum(b.total) from Booking b where year(b"
          + ".bookingDate)=:year and b.payment!=null")
  List<Object[]> getRevenueAnnual(int year);

  @Query(
      "select count(b), sum(b.total) from Booking b where month(b"
          + ".bookingDate)=:month and year(b.bookingDate)=:year and"
          + " b.payment!=null")
  BookingStatisticsResult getRevenueMonthly(int month, int year);

  @Query(
      "select month(b.bookingDate), sum(b.total) from Booking b where b.customer.id=?1 and month(b.bookingDate) between ?2 and ?3 group by b.bookingDate order by b.bookingDate")
  List<Object[]> sumBookingTotalByCustomerId(
      Integer customerId, Integer fromMonth, Integer toMonth);

  @Query(
      "select day (b.bookingDate), sum(b.total) from Booking b where b.customer.id=?1 and month(b.bookingDate) =?2 and year(b.bookingDate)=?3 group by day (b.bookingDate) order by b.bookingDate")
  List<Object[]> sumBookingTotalInMonthByCustomerId(
      Integer customerId, Integer month, Integer year);

  @Query(
      "select b.bookingDate, sum(b.total) from Booking b where b.customer.id = ?1 and b.bookingDate between ?2 and ?3 group by b.bookingDate order by b.bookingDate")
  List<Object[]> sumTotalBookingByCustomerId(
      Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);

  @Transactional
  @Modifying
  @Query("update Booking b set b.payment = ?1 where b.id = ?2")
  int update(Boolean payment, Integer id);

  @Transactional
  @Modifying
  @Query("update Booking b set b.total = ?1, b.payment = ?2, b.quantity = ?3 where b.id = ?4")
  int update(BigDecimal total, Boolean payment, Integer quantity, Integer id);
}
