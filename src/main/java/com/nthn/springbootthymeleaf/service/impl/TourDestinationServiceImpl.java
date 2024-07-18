package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.TourDestination;
import com.nthn.springbootthymeleaf.repository.TourDestinationRepository;
import com.nthn.springbootthymeleaf.service.TourDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class TourDestinationServiceImpl implements TourDestinationService {

    @Autowired
    private TourDestinationRepository tourDestinationRepository;


    public void delete(Integer id) {
        tourDestinationRepository.deleteById(id);
    }


    private TourDestination requireOne(Integer id) {
        return tourDestinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
