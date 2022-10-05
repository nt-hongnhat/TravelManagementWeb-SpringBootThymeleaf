package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.DistrictDTO;
import com.nthn.springbootthymeleaf.VO.DistrictQueryVO;
import com.nthn.springbootthymeleaf.VO.DistrictUpdateVO;
import com.nthn.springbootthymeleaf.VO.DistrictVO;
import com.nthn.springbootthymeleaf.model.District;
import com.nthn.springbootthymeleaf.repository.DistrictRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public String save(DistrictVO vO) {
        District bean = new District();
        BeanUtils.copyProperties(vO, bean);
        bean = districtRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        districtRepository.deleteById(id);
    }

    public void update(String id, DistrictUpdateVO vO) {
        District bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        districtRepository.save(bean);
    }

    public DistrictDTO getById(String id) {
        District original = requireOne(id);
        return toDTO(original);
    }

    public Page<DistrictDTO> query(DistrictQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DistrictDTO toDTO(District original) {
        DistrictDTO bean = new DistrictDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private District requireOne(String id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<DistrictDTO> getAll() {
        return null;
    }
}
