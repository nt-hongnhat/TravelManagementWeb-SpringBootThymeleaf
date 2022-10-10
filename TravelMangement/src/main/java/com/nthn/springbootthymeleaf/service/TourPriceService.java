package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourPrice;
import com.nthn.springbootthymeleaf.repository.TourPriceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourPriceService {

    @Autowired
    private TourPriceRepository tourPriceRepository;

    public Integer save(TourPrice tourPrice) {
        TourPrice bean = new TourPrice();
        BeanUtils.copyProperties(tourPrice, bean);
        bean = tourPriceRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        tourPriceRepository.deleteById(id);
    }

    public void update(Integer id, TourPrice tourPrice) {
        TourPrice bean = requireOne(id);
        BeanUtils.copyProperties(tourPrice, bean);
        tourPriceRepository.save(bean);
    }

    public TourPrice getById(Integer id) {
        return (requireOne(id));
    }


    private TourPrice requireOne(Integer id) {
        return tourPriceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<TourPriceDTO> query(TourPriceQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private TourPriceDTO toDTO(TourPrice original) {
//        TourPriceDTO bean = new TourPriceDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
