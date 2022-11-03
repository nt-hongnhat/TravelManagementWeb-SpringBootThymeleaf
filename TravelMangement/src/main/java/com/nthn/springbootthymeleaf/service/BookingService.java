package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.BookingTourDTO;
import com.nthn.springbootthymeleaf.DTO.SearchHistoryDTO;
import com.nthn.springbootthymeleaf.pojo.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface BookingService {

    Booking save(Booking booking);

    Booking add(BookingTourDTO bookingTourDTO);

    void delete(Integer id);

    void update(Integer id, Booking booking);

    Booking getById(Integer id);

    List<Booking> getBookings(LocalDateTime fromDate, LocalDateTime toDate, Integer customerId);

    List<Booking> getBookingsByCustomer(Integer customerId);

    List<Object[]> sumBookingTotalByCustomerId(Integer customerId, Integer fromMonth, Integer toMonth);

    List<Object[]> sumBookingTotalInMonthByCustomerId(Integer customerId, Integer month, Integer year);

    List<Object[]> sumTotalBookingByCustomerId(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);

    Page<Booking> findBookingByCustomer(Integer customerId, LocalDate fromDate, LocalDate toDate, Pageable pageable);


    Page<Booking> findByCustomerId(Integer customerId, Pageable pageable);

    Page<Booking> findByCustomerId(Integer customerId, LocalDate fromDate, LocalDate toDate, boolean payment, Pageable pageable);
}
