package com.nthn.springbootthymeleaf;

import com.nthn.springbootthymeleaf.pojo.Booking;
import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.repository.BookingDetailRepository;
import com.nthn.springbootthymeleaf.repository.BookingRepository;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;

@SpringBootTest
class TravelMangementApplicationTests {
    @Autowired
    BookingDetailRepository bookingDetailRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    TourTicketRepository tourTicketRepository;

    @Transactional
    @Test
    void contextLoads() {

    }

}
