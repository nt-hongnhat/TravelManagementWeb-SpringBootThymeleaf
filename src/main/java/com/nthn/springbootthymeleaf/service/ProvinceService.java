package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Province;

import java.util.List;

public interface ProvinceService {
    Province getProvince(Integer id);

    List<Province> getProvinces();

    List<Province> getProvinces(String keyword);


}
