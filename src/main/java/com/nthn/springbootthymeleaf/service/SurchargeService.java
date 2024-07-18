package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Surcharge;

public interface SurchargeService {

    Integer save(Surcharge surcharge);

    void delete(Integer id);

    void update(Integer id, Surcharge surcharge);

    Surcharge getById(Integer id);
}
