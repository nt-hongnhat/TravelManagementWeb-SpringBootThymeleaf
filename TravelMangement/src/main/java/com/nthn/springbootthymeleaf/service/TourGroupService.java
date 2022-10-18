package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.TourGroup;
import com.nthn.springbootthymeleaf.repository.TourGroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TourGroupService {

    @Autowired
    private TourGroupRepository tourGroupRepository;

    public Integer save(TourGroup vO) {
        TourGroup bean = new TourGroup();
        BeanUtils.copyProperties(vO, bean);
        bean = tourGroupRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        tourGroupRepository.deleteById(id);
    }


    private TourGroup requireOne(Integer id) {
        return tourGroupRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public TourGroup getTourGroup(String keyword) {
        return tourGroupRepository.findByLinkStatic(keyword);
    }

    public List<TourGroup> getTourGroups(String keyword) {
        return tourGroupRepository.findAll();
    }
}
