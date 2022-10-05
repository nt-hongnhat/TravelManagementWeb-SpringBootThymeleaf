package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.TourDTO;
import com.nthn.springbootthymeleaf.VO.TourQueryVO;
import com.nthn.springbootthymeleaf.VO.TourUpdateVO;
import com.nthn.springbootthymeleaf.VO.TourVO;
import com.nthn.springbootthymeleaf.model.Tour;
import com.nthn.springbootthymeleaf.repository.TourRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public Integer save(TourVO vO) {
        Tour bean = new Tour();
        BeanUtils.copyProperties(vO, bean);
        bean = tourRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        tourRepository.deleteById(id);
    }

    public void update(Integer id, TourUpdateVO vO) {
        Tour bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        tourRepository.save(bean);
    }

    public TourDTO getById(Integer id) {
        Tour original = requireOne(id);
        return toDTO(original);
    }

    public Page<TourDTO> query(TourQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TourDTO toDTO(Tour original) {
        TourDTO bean = new TourDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Tour requireOne(Integer id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
