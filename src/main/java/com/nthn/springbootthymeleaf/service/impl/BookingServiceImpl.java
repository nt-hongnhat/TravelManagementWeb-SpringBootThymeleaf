package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.dto.SearchHistoryDTO;
import com.nthn.springbootthymeleaf.entity.Booking;
import com.nthn.springbootthymeleaf.entity.BookingDetail;
import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.entity.TourTicket;
import com.nthn.springbootthymeleaf.repository.BookingRepository;
import com.nthn.springbootthymeleaf.service.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
    
    @Autowired
    private DepartureDateService departureDateService;
    
    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    @Override
    public Booking addBooking(Booking booking) {
        AtomicInteger quantity = new AtomicInteger();
        AtomicLong total = new AtomicLong();
        Booking saveBooking = bookingRepository.save(booking);
        List<BookingDetail> bookingDetails = booking.getBookingDetails();
        
        Booking finalSaveBooking = saveBooking;
        bookingDetails.forEach(bookingDetail -> {
            if (bookingDetail.getQuantity() != 0) {
                bookingDetail.setBooking(finalSaveBooking);
                TourTicket tourTicket = tourTicketService.findTourTicketById(bookingDetail.getTourTicket().getId());
                bookingDetail.setTourTicket(tourTicket);
                bookingDetail.setTotal(BigDecimal.valueOf(
                        (long) bookingDetail.getUnitPrice().intValue() * bookingDetail.getQuantity()));
                System.out.println("BookingDetail: " + bookingDetail);
                bookingDetail = bookingDetailService.save(bookingDetail);
                quantity.addAndGet(bookingDetail.getQuantity());
                total.addAndGet(bookingDetail.getTotal().longValue());
            }
        });
        saveBooking.setQuantity(quantity.get());
        saveBooking.setTotal(BigDecimal.valueOf(total.get()));
        saveBooking = bookingService.save(saveBooking);
        DepartureDate departureDate = saveBooking.getDepartureDate();
        departureDate.setAvailableSlot(departureDate.getAvailableSlot() - saveBooking.getQuantity());
        departureDateService.update(departureDate.getId(), departureDate);
        
        return saveBooking;
    }
    
    @Override
    public void payment(@NotNull Booking booking) {
        bookingRepository.update(true, booking.getId());
    }
    
    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }
    
    @Override
    public void update(Integer id, Booking booking) {
        Booking bean = requireOne(id);
        BeanUtils.copyProperties(booking, bean, "bookingDate");
        bookingRepository.save(bean);
    }
    
    @Override
    public Booking getById(Integer id) {
        return bookingRepository.getReferenceById(id);
    }
    
    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }
    
    @Override
    public List<Booking> getAll(boolean isPayment) {
        if (isPayment) return bookingRepository.findByPaymentNotNull();
        return bookingRepository.findByPaymentNull();
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
    public List<Object[]> sumTotalBookingByCustomerId(Integer customerId, LocalDateTime fromDate,
                                                      LocalDateTime toDate) {
        return bookingRepository.sumTotalBookingByCustomerId(customerId, fromDate, toDate);
    }
    
    @Override
    public Page<Booking> findByCustomerId(Integer customerId, Pageable pageable) {
        return bookingRepository.findByCustomerId(customerId, pageable);
    }
    
    @Override
    public Page<Booking> findBookingByCustomer(Integer customerId, LocalDate fromDate, LocalDate toDate,
                                               Pageable pageable) {
        return bookingRepository.findByCustomerId(customerId, fromDate, toDate, pageable);
    }
    
    @Override
    public Page<Booking> findByCustomerId(Integer customerId, LocalDate fromDate, LocalDate toDate, boolean payment,
                                          Pageable pageable) {
        if (payment) return bookingRepository.findByCustomerIdPaid(customerId, fromDate, toDate, pageable);
        return bookingRepository.findByCustomerIdNoPaid(customerId, fromDate, toDate, pageable);
    }
    
    @Override
    public Page<Booking> findByCustomerId(Integer customerId, @NotNull SearchHistoryDTO searchHistoryDTO,
                                          Pageable pageable) {
        try {
            boolean isPayment = Boolean.parseBoolean(searchHistoryDTO.getStatusPayment());
            if (isPayment) {
                return bookingRepository.findByCustomerIdPaid(customerId, searchHistoryDTO.getFromDate(),
                                                              searchHistoryDTO.getToDate(), pageable);
            } else return bookingRepository.findByCustomerIdNoPaid(customerId, searchHistoryDTO.getFromDate(),
                                                                   searchHistoryDTO.getToDate(), pageable);
        } catch (ParseException e) {
            return bookingRepository.findByCustomerId(customerId, searchHistoryDTO.getFromDate(),
                                                      searchHistoryDTO.getToDate(), pageable);
        }
    }
    
    
    private Booking requireOne(Integer id) {
        return bookingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Booking not found: " + id));
    }
    
}
