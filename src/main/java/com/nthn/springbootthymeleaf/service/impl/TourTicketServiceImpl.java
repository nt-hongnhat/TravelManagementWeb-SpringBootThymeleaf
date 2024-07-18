package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.TourTicket;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import com.nthn.springbootthymeleaf.service.TourTicketService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TourTicketServiceImpl implements TourTicketService {
    @Autowired
    private TourTicketRepository tourTicketRepository;
    
    @Override
    public TourTicket findTourTicketById(int id) {
        return tourTicketRepository.findById(id).get();
    }
    
    @Override
    public TourTicket save(TourTicket tourTicket) {
        return tourTicketRepository.save(tourTicket);
    }
    
    @Override
    public List<TourTicket> findAll() {
        return List.of();
    }
    
    @Override
    public List<TourTicket> getTourTicketsByTour(Integer tourId) {
        return tourTicketRepository.findByTourId(tourId);
    }
    
    @Override
    public void update(int id, @NotNull TourTicket tourTicket) {
        TourTicket tourTicket1 = tourTicketRepository.findById(id).get();
        BeanUtils.copyProperties(tourTicket, tourTicket1);
        tourTicketRepository.save(tourTicket1);
    }
}
