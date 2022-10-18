package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourDestination;
import com.nthn.springbootthymeleaf.repository.TourDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourDestinationService {

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
