package com.nthn.travelmanagement.service;

import com.nthn.travelmanagement.DTO.ProvinceQueryVO;
import com.nthn.travelmanagement.DTO.ProvinceUpdateVO;
import com.nthn.travelmanagement.DTO.ProvinceVO;
import com.nthn.travelmanagement.model.Province;
import com.nthn.travelmanagement.repository.impl.ProvinceRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepositoryImpl provinceRepositoryImpl;

    public String save(ProvinceVO vO) {
        Province bean = new Province();
        BeanUtils.copyProperties(vO, bean);
        bean = provinceRepositoryImpl.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        provinceRepositoryImpl.deleteById(id);
    }

    public void update(String id, ProvinceUpdateVO vO) {
        Province bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        provinceRepositoryImpl.save(bean);
    }

    public Province getById(String id) {
        Province original = requireOne(id);
        return to(original);
    }

    public Page<ProvinceDTO> query(ProvinceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private Province to(Province original) {
        Province bean = new Province();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Province requireOne(String id) {
        return provinceRepositoryImpl.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
