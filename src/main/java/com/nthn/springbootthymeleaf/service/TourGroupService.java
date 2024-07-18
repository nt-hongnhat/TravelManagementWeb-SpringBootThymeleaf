package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.TourGroup;

import java.util.List;

public interface TourGroupService {
    Integer save(TourGroup tourGroup);

    void delete(Integer id);

    TourGroup getTourGroup(Integer id);

    TourGroup getTourGroup(String keyword);

    List<TourGroup> getTourGroups();

    List<TourGroup> getTourGroupsByCategory(String linkStatic);
}
