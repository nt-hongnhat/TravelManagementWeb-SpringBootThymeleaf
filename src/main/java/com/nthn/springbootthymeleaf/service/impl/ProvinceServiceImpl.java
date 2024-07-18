package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Province;
import com.nthn.springbootthymeleaf.repository.ProvinceRepository;
import com.nthn.springbootthymeleaf.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    // READ
    @Override
    public Province getProvince(Integer id) {
        Optional<Province> province = provinceRepository.findById(id);
        return province.orElse(null);
    }

    @Override
    public List<Province> getProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<Province> getProvinces(String keyword) {
        return provinceRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }
}
