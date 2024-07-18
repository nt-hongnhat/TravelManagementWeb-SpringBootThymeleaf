package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.TourSchedule;
import com.nthn.springbootthymeleaf.repository.TourScheduleRepository;
import com.nthn.springbootthymeleaf.service.TourScheduleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TourScheduleServiceImpl implements TourScheduleService {
    
    @Autowired
    private TourScheduleRepository tourScheduleRepository;
    
    
    public void delete(Integer id) {
        tourScheduleRepository.deleteById(id);
    }
    
    
    @Override
    public TourSchedule update(int id, @NotNull TourSchedule tourSchedule) {
        TourSchedule tourSchedule1 = tourScheduleRepository.findById(id).get();
        BeanUtils.copyProperties(tourSchedule, tourSchedule1);
        return tourScheduleRepository.save(tourSchedule1);
    }
    
    @Override
    public void updateAll(@NotNull List<TourSchedule> tourSchedules) {
        tourSchedules.forEach(this::accept);
    }
    
    
    private TourSchedule requireOne(Integer id) {
        return tourScheduleRepository.findById(id)
                                     .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
    
    private void accept(TourSchedule tourSchedule) {update(tourSchedule.getId(), tourSchedule);}
}
