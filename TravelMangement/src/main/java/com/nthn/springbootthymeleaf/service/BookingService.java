package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Booking;
import com.nthn.springbootthymeleaf.repository.BookingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Integer save(Booking booking) {
        Booking bean = new Booking();
        BeanUtils.copyProperties(booking, bean);
        bean = bookingRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    public void update(Integer id, Booking booking) {
        Booking bean = requireOne(id);
        BeanUtils.copyProperties(booking, bean);
        bookingRepository.save(bean);
    }

    public Booking getById(Integer id) {
        return (requireOne(id));
    }

//    public Page<BookingDTO> query(BookingQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }


    private Booking requireOne(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<BookingDTO> query(BookingQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private BookingDTO toDTO(Booking original) {
//        BookingDTO bean = new BookingDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
