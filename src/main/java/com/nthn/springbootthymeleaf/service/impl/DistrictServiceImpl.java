package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.District;
import com.nthn.springbootthymeleaf.repository.DistrictRepository;
import com.nthn.springbootthymeleaf.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    // READ
    public District read(Integer id) {
        Optional<District> district = districtRepository.findById(id);
        return district.orElse(null);
    }

    public List<District> read(String keyword) {
        if (keyword == null) {
            return districtRepository.findAll(Sort.by("name").ascending());
        }
        return districtRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }
}
