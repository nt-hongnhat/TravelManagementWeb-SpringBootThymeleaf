package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.BookingDetailDTO;
import com.nthn.springbootthymeleaf.VO.BookingDetailQueryVO;
import com.nthn.springbootthymeleaf.VO.BookingDetailUpdateVO;
import com.nthn.springbootthymeleaf.VO.BookingDetailVO;
import com.nthn.springbootthymeleaf.model.BookingDetail;
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

    public String save(BookingDetailVO vO) {
        BookingDetail bean = new BookingDetail();
        BeanUtils.copyProperties(vO, bean);
        bean = bookingDetailRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        bookingDetailRepository.deleteById(id);
    }

    public void update(String id, BookingDetailUpdateVO vO) {
        BookingDetail bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        bookingDetailRepository.save(bean);
    }

    public BookingDetailDTO getById(String id) {
        BookingDetail original = requireOne(id);
        return toDTO(original);
    }

    public Page<BookingDetailDTO> query(BookingDetailQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private BookingDetailDTO toDTO(BookingDetail original) {
        BookingDetailDTO bean = new BookingDetailDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private BookingDetail requireOne(String id) {
        return bookingDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
