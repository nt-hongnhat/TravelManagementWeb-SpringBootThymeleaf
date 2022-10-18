package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourTicket;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourTicketService {

    @Autowired
    private TourTicketRepository tourTicketRepository;


    private TourTicket requireOne(Integer id) {
        return tourTicketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
