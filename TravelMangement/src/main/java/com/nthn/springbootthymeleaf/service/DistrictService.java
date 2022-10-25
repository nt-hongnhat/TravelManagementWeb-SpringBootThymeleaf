package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.District;

import java.util.List;

public interface DistrictService {
    District read(Integer id);

    List<District> read(String keyword);
}
