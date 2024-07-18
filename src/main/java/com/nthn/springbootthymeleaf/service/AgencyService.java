package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Agency;

import java.util.List;

public interface AgencyService {
    Agency create(Agency agency);

    Agency read(Integer id);

    List<Agency> read(String keyword);

    Agency update(Integer id, Agency agency);

    void delete(Integer id);

}
