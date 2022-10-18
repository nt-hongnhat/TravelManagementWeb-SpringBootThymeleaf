package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Places;
import com.nthn.springbootthymeleaf.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlacesService {

    @Autowired
    private PlacesRepository placesRepository;

    public List<Places> getPlaces() {
        return placesRepository.findAll();
    }


    public void delete(Integer id) {
        placesRepository.deleteById(id);
    }


    private Places requireOne(Integer id) {
        return placesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
