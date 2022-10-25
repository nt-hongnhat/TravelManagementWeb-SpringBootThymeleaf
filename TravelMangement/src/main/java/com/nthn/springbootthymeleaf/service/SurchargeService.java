package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Surcharge;

public interface SurchargeService {

    Integer save(Surcharge surcharge);

    void delete(Integer id);

    void update(Integer id, Surcharge surcharge);

    Surcharge getById(Integer id);
}
