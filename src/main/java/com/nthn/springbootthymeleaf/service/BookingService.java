package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.dto.SearchHistoryDTO;
import com.nthn.springbootthymeleaf.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface BookingService {
    
    Booking save(Booking booking);
    
    Booking addBooking(Booking booking);
    
    void payment(Booking booking);
    
    void delete(Integer id);
    
    void update(Integer id, Booking booking);
    
    Booking getById(Integer id);
    List<Booking> getAll();
    
    List<Booking> getAll(boolean isPayment);
    
    List<Booking> getBookings(LocalDateTime fromDate, LocalDateTime toDate, Integer customerId);
    
    List<Booking> getBookingsByCustomer(Integer customerId);
    
    List<Object[]> sumBookingTotalByCustomerId(Integer customerId, Integer fromMonth, Integer toMonth);
    
    List<Object[]> sumBookingTotalInMonthByCustomerId(Integer customerId, Integer month, Integer year);
    
    List<Object[]> sumTotalBookingByCustomerId(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);
    
    Page<Booking> findBookingByCustomer(Integer customerId, LocalDate fromDate, LocalDate toDate, Pageable pageable);
    
    
    Page<Booking> findByCustomerId(Integer customerId, Pageable pageable);
    
    
    Page<Booking> findByCustomerId(Integer customerId, LocalDate fromDate, LocalDate toDate, boolean payment,
                                   Pageable pageable);
    
    Page<Booking> findByCustomerId(Integer customerId, SearchHistoryDTO searchHistoryDTO, Pageable pageable);
}
