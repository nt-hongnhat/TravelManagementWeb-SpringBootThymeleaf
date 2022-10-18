package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.repository.BookingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailService {

    @Autowired
    private BookingDetailRepository bookingDetailRepository;


}
