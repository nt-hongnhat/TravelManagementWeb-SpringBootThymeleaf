package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Places;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlacesService {
    List<Places> getPlaces();

    void delete(Integer id);

    Page<Places> getPlacesPage(Pageable pageable);

}
