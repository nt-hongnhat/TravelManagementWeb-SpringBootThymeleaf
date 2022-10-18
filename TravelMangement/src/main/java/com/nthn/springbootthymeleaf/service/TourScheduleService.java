package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourSchedule;
import com.nthn.springbootthymeleaf.repository.TourScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourScheduleService {

    @Autowired
    private TourScheduleRepository tourScheduleRepository;


    public void delete(Integer id) {
        tourScheduleRepository.deleteById(id);
    }

    public void update(Integer id, TourSchedule vO) {
        TourSchedule bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        tourScheduleRepository.save(bean);
    }

    private TourSchedule requireOne(Integer id) {
        return tourScheduleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
