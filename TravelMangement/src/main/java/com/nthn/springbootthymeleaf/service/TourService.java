package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.repository.TourRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;

    public Integer save(Tour tour) {
        Tour bean = new Tour();
        BeanUtils.copyProperties(tour, bean);
        bean = tourRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        tourRepository.deleteById(id);
    }

    public void update(Integer id, Tour tour) {
        Tour bean = requireOne(id);
        BeanUtils.copyProperties(tour, bean);
        tourRepository.save(bean);
    }

    public Tour getById(Integer id) {
        return requireOne(id);
    }

    public Page<Tour> getTours(Integer categoryId, Pageable pageable) {
        return tourRepository.findByCategoryId(categoryId, pageable);
    }

    public List<Tour> getTours() {
        return tourRepository.findAll();
    }

    private Tour requireOne(Integer id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


//    public Page<TourDTO> query(TourQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private TourDTO toDTO(Tour original) {
//        TourDTO bean = new TourDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
