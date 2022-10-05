package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.ProvinceDTO;
import com.nthn.springbootthymeleaf.VO.ProvinceQueryVO;
import com.nthn.springbootthymeleaf.VO.ProvinceUpdateVO;
import com.nthn.springbootthymeleaf.VO.ProvinceVO;
import com.nthn.springbootthymeleaf.model.Province;
import com.nthn.springbootthymeleaf.repository.ProvinceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public String save(ProvinceVO vO) {
        Province bean = new Province();
        BeanUtils.copyProperties(vO, bean);
        bean = provinceRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        provinceRepository.deleteById(id);
    }

    public void update(String id, ProvinceUpdateVO vO) {
        Province bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        provinceRepository.save(bean);
    }

    public ProvinceDTO getById(String id) {
        Province original = requireOne(id);
        return toDTO(original);
    }

    public Page<ProvinceDTO> query(ProvinceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ProvinceDTO toDTO(Province original) {
        ProvinceDTO bean = new ProvinceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Province requireOne(String id) {
        return provinceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public Object getAll() {
        return null;
    }
}
