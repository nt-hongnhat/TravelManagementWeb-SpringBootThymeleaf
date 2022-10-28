package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.BookingDetail;

import java.util.List;

public interface BookingDetailService {
    List<BookingDetail> getBookingDetails(Integer id);

    BookingDetail save(BookingDetail bookingDetail);

}
