package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.BookingDetail;
import com.nthn.springbootthymeleaf.repository.BookingDetailRepository;
import com.nthn.springbootthymeleaf.service.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingDetailServiceImpl implements BookingDetailService {
    @Autowired
    private BookingDetailRepository bookingDetailRepository;


    /**
     * @param id
     * @return
     */
    @Override
    public List<BookingDetail> getBookingDetails(Integer id) {
        return null;
    }
}
