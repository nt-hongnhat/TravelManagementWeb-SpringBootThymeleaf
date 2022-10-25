package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourTicket;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

public interface TourTicketService {

    List<TourTicket> getTourTickets(Integer bookingId);

}
