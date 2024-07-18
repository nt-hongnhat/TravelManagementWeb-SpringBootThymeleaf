package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Places;
import com.nthn.springbootthymeleaf.repository.PlacesRepository;
import com.nthn.springbootthymeleaf.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PlacesServiceImpl implements PlacesService {

    @Autowired
    private PlacesRepository placesRepository;

    public List<Places> getPlaces() {
        return placesRepository.findAll();
    }


    public void delete(Integer id) {
        placesRepository.deleteById(id);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<Places> getPlacesPage(Pageable pageable) {
        return placesRepository.findAll(pageable);
    }


    private Places requireOne(Integer id) {
        return placesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
