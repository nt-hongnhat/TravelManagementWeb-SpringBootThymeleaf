package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.BookingDetail;
import com.nthn.springbootthymeleaf.repository.BookingDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookingDetailService {

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    public String save(BookingDetail bookingDetail) {
        BookingDetail bean = new BookingDetail();
        BeanUtils.copyProperties(bookingDetail, bean);
        bean = bookingDetailRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        bookingDetailRepository.deleteById(id);
    }

    public void update(String id, BookingDetail bookingDetail) {
        BookingDetail bean = requireOne(id);
        BeanUtils.copyProperties(bookingDetail, bean);
        bookingDetailRepository.save(bean);
    }

    public BookingDetail getById(String id) {
        return (requireOne(id));
    }

//    public Page<BookingDetailDTO> query(BookingDetailQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }


    private BookingDetail requireOne(String id) {
        return bookingDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<BookingDetailDTO> query(BookingDetailQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private BookingDetailDTO toDTO(BookingDetail original) {
//        BookingDetailDTO bean = new BookingDetailDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
