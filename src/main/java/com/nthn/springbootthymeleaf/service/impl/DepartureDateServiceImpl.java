package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.repository.DepartureDateRepository;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartureDateServiceImpl implements DepartureDateService {
    @Autowired
    private DepartureDateRepository departureDateRepository;
    
    @Override
    public DepartureDate getDepartureDate(String departureDate) {
        return null;
    }
    
    @Override
    public DepartureDate saveDepartureDate(DepartureDate departureDate) {
        return departureDateRepository.save(departureDate);
    }
    
    @Override
    public DepartureDate create(@NotNull DepartureDate departureDate) {
        return departureDateRepository.save(departureDate);
    }
    
    @Override
    public void delete(int departureDateId) {
        departureDateRepository.deleteById(departureDateId);
        
    }
    
    @Override
    public void update(int id, DepartureDate departureDate) {
        DepartureDate departureDate1 = departureDateRepository.findById(id).get();
        BeanUtils.copyProperties(departureDate, departureDate1);
        departureDateRepository.save(departureDate1);
    }
}
