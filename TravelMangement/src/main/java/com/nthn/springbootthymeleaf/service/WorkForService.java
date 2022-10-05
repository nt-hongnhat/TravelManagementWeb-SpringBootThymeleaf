package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.WorkForDTO;
import com.nthn.springbootthymeleaf.VO.WorkForQueryVO;
import com.nthn.springbootthymeleaf.VO.WorkForUpdateVO;
import com.nthn.springbootthymeleaf.VO.WorkForVO;
import com.nthn.springbootthymeleaf.model.WorkFor;
import com.nthn.springbootthymeleaf.repository.WorkForRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WorkForService {

    @Autowired
    private WorkForRepository workForRepository;

    public Integer save(WorkForVO vO) {
        WorkFor bean = new WorkFor();
        BeanUtils.copyProperties(vO, bean);
        bean = workForRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        workForRepository.deleteById(id);
    }

    public void update(Integer id, WorkForUpdateVO vO) {
        WorkFor bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        workForRepository.save(bean);
    }

    public WorkForDTO getById(Integer id) {
        WorkFor original = requireOne(id);
        return toDTO(original);
    }

    public Page<WorkForDTO> query(WorkForQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private WorkForDTO toDTO(WorkFor original) {
        WorkForDTO bean = new WorkForDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private WorkFor requireOne(Integer id) {
        return workForRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
