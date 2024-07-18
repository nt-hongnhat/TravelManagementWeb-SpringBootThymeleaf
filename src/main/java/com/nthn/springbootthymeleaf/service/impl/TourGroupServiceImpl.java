package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.TourGroup;
import com.nthn.springbootthymeleaf.repository.TourGroupRepository;
import com.nthn.springbootthymeleaf.service.TourGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourGroupServiceImpl implements TourGroupService {

    @Autowired
    private TourGroupRepository tourGroupRepository;


    @Override
    public Integer save(TourGroup tourGroup) {
        TourGroup model = tourGroupRepository.save(tourGroup);
        return model.getId();
    }

    @Override
    public void delete(Integer id) {
        tourGroupRepository.deleteById(id);
    }

    @Override
    public TourGroup getTourGroup(Integer id) {
        return tourGroupRepository.findById(id).get();
    }

    @Override
    public TourGroup getTourGroup(String keyword) {
        return tourGroupRepository.findByLinkStatic(keyword);
    }

    @Override
    public List<TourGroup> getTourGroups() {
        return tourGroupRepository.findAll();
    }

    @Override
    public List<TourGroup> getTourGroupsByCategory(String linkStatic) {
        return tourGroupRepository.getByCategoryLinkStatic(linkStatic);
    }
}
