package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.DTO.BookingTourDTO;
import com.nthn.springbootthymeleaf.pojo.Booking;
import com.nthn.springbootthymeleaf.pojo.BookingDetail;
import com.nthn.springbootthymeleaf.pojo.Customer;
import com.nthn.springbootthymeleaf.pojo.TourTicket;
import com.nthn.springbootthymeleaf.repository.BookingRepository;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private TourService tourService;
    @Autowired
    private TourTicketService tourTicketService;
    @Autowired
    private CustomerService customerService;

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking add(BookingTourDTO bookingTourDTO) {
        Booking booking = new Booking();

        Customer customer = customerService.getCustomer(bookingTourDTO.getIdentified());
        if (customer == null) {
            customer = customerService.add(bookingTourDTO.getFullName(), bookingTourDTO.getIdentified(), bookingTourDTO.getPhone(), bookingTourDTO.getAddress());
        }

        booking.setTour(tourService.getById(bookingTourDTO.getTourId()));
        booking.setCustomer(customer);

        booking = bookingRepository.save(booking);

        List<BookingDetail> bookingDetails = new ArrayList<>();
        List<TourTicket> tourTickets = tourTicketService.getTourTicketsByTour(bookingTourDTO.getTourId());
        List<Integer> quantities = new ArrayList<>();
        quantities.add(bookingTourDTO.getNumberAdult());
        quantities.add(bookingTourDTO.getNumberChildren());
        quantities.add(bookingTourDTO.getNumberYoungChildren());
        quantities.add(bookingTourDTO.getNumberInfants());
        int size = tourTickets.size();
        for (int i = 0; i < size; i++) {
            if (quantities.get(i) > 0) {
                BookingDetail bookingDetail = new BookingDetail();
                bookingDetail.setBooking(booking);
                bookingDetail.setTourTicket(tourTickets.get(i));
                bookingDetail.setUnitPrice(tourTickets.get(i).getUnitPrice());
                bookingDetail.setQuantity(quantities.get(i));

                bookingDetails.add(bookingDetailService.save(bookingDetail));
            }
        }

        bookingDetails.forEach(System.out::println);
        return booking;
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
        return bookingRepository.getReferenceById(id);
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

    @Override
    public Page<Booking> findByCustomerId(Integer customerId, Pageable pageable) {
        return bookingRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public Page<Booking> findBookingByCustomer(Integer customerId, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return bookingRepository.findByCustomerId(customerId, fromDate, toDate, pageable);
    }

    @Override
    public Page<Booking> findByCustomerId(Integer customerId, LocalDate fromDate, LocalDate toDate, boolean payment, Pageable pageable) {
        return bookingRepository.findByCustomerId(customerId, fromDate, toDate, payment, pageable);
    }


    private Booking requireOne(Integer id) {
        return bookingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Booking not found: " + id));
    }

}
