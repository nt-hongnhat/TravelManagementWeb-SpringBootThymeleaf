package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourSchedule;
import org.springframework.beans.BeanUtils;

public interface TourScheduleService {
    void delete(Integer id);

    void update(Integer id, TourSchedule vO);

}
