package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.Booking;
import com.nthn.springbootthymeleaf.repository.BookingRepository;
import com.nthn.springbootthymeleaf.service.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking save(Booking booking) {

        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, Booking booking) {
        Booking bean = requireOne(id);
        BeanUtils.copyProperties(booking, bean);
        bookingRepository.save(bean);
    }

    @Override
    public Booking getById(Integer id) {
        return (requireOne(id));
    }


    @Override
    public List<Booking> getBookings(LocalDateTime fromDate, LocalDateTime toDate, Integer customerId) {
        return bookingRepository.findByBookingDateBetweenAndCustomerId(fromDate, toDate, customerId);
    }

    @Override
    public List<Booking> getBookingsByCustomer(Integer customerId) {
        return bookingRepository.getBookingsByCustomerIdOrderByBookingDate(customerId);
    }

    @Override
    public List<Object[]> sumBookingTotalByCustomerId(Integer customerId, Integer fromMonth, Integer toMonth) {
        return bookingRepository.sumBookingTotalByCustomerId(customerId, fromMonth, toMonth);
    }

    @Override
    public List<Object[]> sumBookingTotalInMonthByCustomerId(Integer customerId, Integer month, Integer year) {
        return bookingRepository.sumBookingTotalInMonthByCustomerId(customerId, month, year);
    }


    @Override
    public List<Object[]> sumTotalBookingByCustomerId(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate) {
        return bookingRepository.sumTotalBookingByCustomerId(customerId, fromDate, toDate);
    }


    private Booking requireOne(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
