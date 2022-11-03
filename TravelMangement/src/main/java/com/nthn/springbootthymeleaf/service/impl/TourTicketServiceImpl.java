package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.TourTicket;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import com.nthn.springbootthymeleaf.service.TourTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TourTicketServiceImpl implements TourTicketService {
    @Autowired
    private TourTicketRepository tourTicketRepository;

    @Override
    public List<TourTicket> getTourTicketsByTour(Integer tourId) {
        return tourTicketRepository.findByTourId(tourId);
    }
}
