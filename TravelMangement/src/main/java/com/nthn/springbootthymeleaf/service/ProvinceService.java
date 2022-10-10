package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Province;
import com.nthn.springbootthymeleaf.repository.ProvinceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    // READ
    public Province getProvince(Integer id) {
        Optional<Province> province = provinceRepository.findById(id);
        return province.orElse(null);
    }

    public List<Province> getProvinces(String keyword) {
        if (keyword == null) {
            return provinceRepository.findAll();
        } else return provinceRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }

//    public String save(ProvinceVO vO) {
//        Province bean = new Province();
//        BeanUtils.copyProperties(vO, bean);
//        bean = provinceRepository.save(bean);
//        return bean.getId();
//    }

//    public void delete(String id) {
//        provinceRepository.deleteById(id);
//    }

//    public void update(String id, ProvinceUpdateVO vO) {
//        Province bean = requireOne(id);
//        BeanUtils.copyProperties(vO, bean);
//        provinceRepository.save(bean);
//    }
//
//    public ProvinceDTO getById(String id) {
//        Province original = requireOne(id);
//        return toDTO(original);
//    }
//
//    public Page<ProvinceDTO> query(ProvinceQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private ProvinceDTO toDTO(Province original) {
//        ProvinceDTO bean = new ProvinceDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
//
//    private Province requireOne(String id) {
//        return provinceRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
//    }
}
