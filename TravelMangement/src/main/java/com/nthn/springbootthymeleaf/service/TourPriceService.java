package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.TourPriceDTO;
import com.nthn.springbootthymeleaf.VO.TourPriceQueryVO;
import com.nthn.springbootthymeleaf.VO.TourPriceUpdateVO;
import com.nthn.springbootthymeleaf.VO.TourPriceVO;
import com.nthn.springbootthymeleaf.model.TourPrice;
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

    public Integer save(TourPriceVO vO) {
        TourPrice bean = new TourPrice();
        BeanUtils.copyProperties(vO, bean);
        bean = tourPriceRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        tourPriceRepository.deleteById(id);
    }

    public void update(Integer id, TourPriceUpdateVO vO) {
        TourPrice bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        tourPriceRepository.save(bean);
    }

    public TourPriceDTO getById(Integer id) {
        TourPrice original = requireOne(id);
        return toDTO(original);
    }

    public Page<TourPriceDTO> query(TourPriceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TourPriceDTO toDTO(TourPrice original) {
        TourPriceDTO bean = new TourPriceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private TourPrice requireOne(Integer id) {
        return tourPriceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
