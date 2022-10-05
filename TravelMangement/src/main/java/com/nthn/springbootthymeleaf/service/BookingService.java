package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.BookingDTO;
import com.nthn.springbootthymeleaf.VO.BookingQueryVO;
import com.nthn.springbootthymeleaf.VO.BookingUpdateVO;
import com.nthn.springbootthymeleaf.VO.BookingVO;
import com.nthn.springbootthymeleaf.model.Booking;
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

    public Integer save(BookingVO vO) {
        Booking bean = new Booking();
        BeanUtils.copyProperties(vO, bean);
        bean = bookingRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    public void update(Integer id, BookingUpdateVO vO) {
        Booking bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        bookingRepository.save(bean);
    }

    public BookingDTO getById(Integer id) {
        Booking original = requireOne(id);
        return toDTO(original);
    }

    public Page<BookingDTO> query(BookingQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private BookingDTO toDTO(Booking original) {
        BookingDTO bean = new BookingDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Booking requireOne(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
