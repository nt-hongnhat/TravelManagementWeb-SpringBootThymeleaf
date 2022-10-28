package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Booking;

import java.time.LocalDateTime;
import java.util.List;


public interface BookingService {

    Booking save(Booking booking);

    void delete(Integer id);

    void update(Integer id, Booking booking);

    Booking getById(Integer id);

    List<Booking> getBookings(LocalDateTime fromDate, LocalDateTime toDate, Integer customerId);

    List<Booking> getBookingsByCustomer(Integer customerId);

    List<Object[]> sumBookingTotalByCustomerId(Integer customerId, Integer fromMonth, Integer toMonth);

    List<Object[]> sumBookingTotalInMonthByCustomerId(Integer customerId, Integer month, Integer year);

    List<Object[]> sumTotalBookingByCustomerId(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);
}
