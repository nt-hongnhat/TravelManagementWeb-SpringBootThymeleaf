package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.SurchargeDTO;
import com.nthn.springbootthymeleaf.VO.SurchargeQueryVO;
import com.nthn.springbootthymeleaf.VO.SurchargeUpdateVO;
import com.nthn.springbootthymeleaf.VO.SurchargeVO;
import com.nthn.springbootthymeleaf.model.Surcharge;
import com.nthn.springbootthymeleaf.repository.SurchargeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SurchargeService {

    @Autowired
    private SurchargeRepository surchargeRepository;

    public Integer save(SurchargeVO vO) {
        Surcharge bean = new Surcharge();
        BeanUtils.copyProperties(vO, bean);
        bean = surchargeRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        surchargeRepository.deleteById(id);
    }

    public void update(Integer id, SurchargeUpdateVO vO) {
        Surcharge bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        surchargeRepository.save(bean);
    }

    public SurchargeDTO getById(Integer id) {
        Surcharge original = requireOne(id);
        return toDTO(original);
    }

    public Page<SurchargeDTO> query(SurchargeQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private SurchargeDTO toDTO(Surcharge original) {
        SurchargeDTO bean = new SurchargeDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Surcharge requireOne(Integer id) {
        return surchargeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
