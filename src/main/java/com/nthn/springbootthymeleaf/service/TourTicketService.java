package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.TourTicket;

import java.util.List;

public interface TourTicketService {
    TourTicket findTourTicketById(int id);

    TourTicket save(TourTicket tourTicket);

    List<TourTicket> findAll();

    List<TourTicket> getTourTicketsByTour(Integer tourId);

    void update(int id, TourTicket tourTicket);
}
