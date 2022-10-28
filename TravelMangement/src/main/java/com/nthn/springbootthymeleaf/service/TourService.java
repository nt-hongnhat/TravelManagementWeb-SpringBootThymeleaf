package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.SearchTourDTO;
import com.nthn.springbootthymeleaf.pojo.Category;
import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TourService {
    Tour save(Tour tour);

    Tour create(Tour tour) throws ParseException;

    void delete(Integer id);

    void update(Integer id, Tour tour);

    Tour getById(Integer id);

    List<Tour> getTours();

    Page<Tour> getTourPage(Pageable pageable);

    Page<Tour> getTourPageBySearchTour(SearchTourDTO searchTourDTO, Pageable pageable);

//    Page<Tour> getTourPage(Category category, Pageable pageable);

//    Page<Tour> getTourPage(String categoryLink, Pageable pageable);

//    Page<Tour> getTourPage(TourGroup tourGroup, Pageable pageable);

    Page<Tour> getTourPageByCategory(String categoryLink, Pageable pageable);

    Page<Tour> getTourPageByTourGroup(String tourGroupLink, Pageable pageable);

    Page<Tour> getTourPage(String departurePlace, String destinationPlace, String duration, String tourGroupLink, Pageable pageable);

    Page<Tour> getTourPage(Map<String, String> params, Pageable pageable);

    Page<Tour> getTourPageByTourGroup(Set<TourGroup> tourGroups, Pageable pageable);

    Set<String> getDeparturePlaces();

    Set<String> getDurations();
}
