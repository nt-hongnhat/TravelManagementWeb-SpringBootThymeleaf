package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Province;
import com.nthn.springbootthymeleaf.repository.ProvinceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    // READ
    public Province getProvince(Integer id) {
        Optional<Province> province = provinceRepository.findById(id);
        return province.orElse(null);
    }

    public List<Province> getProvinces(String keyword) {
        if (keyword == null) {
            return provinceRepository.findAll();
        } else return provinceRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }
}
