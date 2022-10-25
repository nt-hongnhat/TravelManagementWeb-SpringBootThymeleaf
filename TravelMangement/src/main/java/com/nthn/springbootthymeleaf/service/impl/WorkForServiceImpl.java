package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.WorkFor;
import com.nthn.springbootthymeleaf.repository.WorkForRepository;
import com.nthn.springbootthymeleaf.service.WorkForService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WorkForServiceImpl implements WorkForService {

    @Autowired
    private WorkForRepository workForRepository;

    public WorkFor save(WorkFor workFor) {
        return workForRepository.save(workFor);
    }

    public void delete(Integer id) {
        workForRepository.deleteById(id);
    }

    public WorkFor update(Integer id, WorkFor vO) {
        WorkFor bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        workForRepository.save(bean);
        return bean;
    }

    public WorkFor getById(Integer id) {
        return (requireOne(id));
    }

    private WorkFor requireOne(Integer id) {
        return workForRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<WorkForDTO> query(WorkForQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private WorkForDTO toDTO(WorkFor original) {
//        WorkForDTO bean = new WorkForDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
