package com.nthn.travelmanagement.service;

import com.nthn.travelmanagement.DTO.WardQueryVO;
import com.nthn.travelmanagement.DTO.WardUpdateVO;
import com.nthn.travelmanagement.DTO.WardVO;
import com.nthn.travelmanagement.model.Ward;
import com.nthn.travelmanagement.repository.impl.WardRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WardService {

    @Autowired
    private WardRepositoryImpl wardRepositoryImpl;

    public String save(WardVO vO) {
        Ward bean = new Ward();
        BeanUtils.copyProperties(vO, bean);
        bean = wardRepositoryImpl.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        wardRepositoryImpl.deleteById(id);
    }

    public void update(String id, WardUpdateVO vO) {
        Ward bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        wardRepositoryImpl.save(bean);
    }

    public Ward getById(String id) {
        Ward original = requireOne(id);
        return to(original);
    }

    public Page<WardDTO> query(WardQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private Ward to(Ward original) {
        Ward bean = new Ward();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Ward requireOne(String id) {
        return wardRepositoryImpl.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
