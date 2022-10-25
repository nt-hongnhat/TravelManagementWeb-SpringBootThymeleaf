package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Agency;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AgencyService {
    Agency create(Agency agency);

    Agency read(Integer id);

    List<Agency> read(String keyword);

    Agency update(Integer id, Agency agency);

    void delete(Integer id);

}
