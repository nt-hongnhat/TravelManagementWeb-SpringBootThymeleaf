package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.District;
import com.nthn.springbootthymeleaf.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

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
