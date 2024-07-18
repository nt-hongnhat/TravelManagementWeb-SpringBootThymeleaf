package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Surcharge;
import com.nthn.springbootthymeleaf.repository.SurchargeRepository;
import com.nthn.springbootthymeleaf.service.SurchargeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class SurchargeServiceImpl implements SurchargeService {

    @Autowired
    private SurchargeRepository surchargeRepository;

    public Integer save(Surcharge surcharge) {
        Surcharge bean = new Surcharge();
        BeanUtils.copyProperties(surcharge, bean);
        bean = surchargeRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        surchargeRepository.deleteById(id);
    }

    public void update(Integer id, Surcharge surcharge) {
        Surcharge bean = requireOne(id);
        BeanUtils.copyProperties(surcharge, bean);
        surchargeRepository.save(bean);
    }

    public Surcharge getById(Integer id) {
        return (requireOne(id));
    }


    private Surcharge requireOne(Integer id) {
        return surchargeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<SurchargeDTO> query(SurchargeQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private SurchargeDTO toDTO(Surcharge original) {
//        SurchargeDTO bean = new SurchargeDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
