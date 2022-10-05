package com.nthn.travelmanagement.service;

import com.nthn.travelmanagement.DTO.DistrictQueryVO;
import com.nthn.travelmanagement.DTO.DistrictUpdateVO;
import com.nthn.travelmanagement.DTO.DistrictVO;
import com.nthn.travelmanagement.model.District;
import com.nthn.travelmanagement.repository.impl.DistrictRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepositoryImpl districtRepositoryImpl;

    public String save(DistrictVO vO) {
        District bean = new District();
        BeanUtils.copyProperties(vO, bean);
        bean = districtRepositoryImpl.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        districtRepositoryImpl.deleteById(id);
    }

    public void update(String id, DistrictUpdateVO vO) {
        District bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        districtRepositoryImpl.save(bean);
    }

    public District getById(String id) {
        District original = requireOne(id);
        return to(original);
    }

    public Page<DistrictDTO> query(DistrictQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private District to(District original) {
        District bean = new District();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private District requireOne(String id) {
        return districtRepositoryImpl.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
