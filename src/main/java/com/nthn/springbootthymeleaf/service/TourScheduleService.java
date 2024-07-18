package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.TourSchedule;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TourScheduleService {
    void delete(Integer id);
    
    TourSchedule update(int id, @NotNull TourSchedule tourSchedule);
    
    void updateAll(List<TourSchedule> tourSchedules);
}
