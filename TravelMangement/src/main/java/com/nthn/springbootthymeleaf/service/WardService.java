package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Ward;
import com.nthn.springbootthymeleaf.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardService {

    @Autowired
    private WardRepository wardRepository;

    public Ward read(Integer id) {
        Optional<Ward> ward = wardRepository.findById(id);
        return ward.orElse(null);
    }

    public List<Ward> read(String keyword) {
        if (keyword == null) {
            return wardRepository.findAll();
        } else return wardRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }
}
