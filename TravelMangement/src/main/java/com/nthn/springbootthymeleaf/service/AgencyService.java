package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.AgencyDTO;
import com.nthn.springbootthymeleaf.VO.AgencyQueryVO;
import com.nthn.springbootthymeleaf.VO.AgencyUpdateVO;
import com.nthn.springbootthymeleaf.VO.AgencyVO;
import com.nthn.springbootthymeleaf.model.Agency;
import com.nthn.springbootthymeleaf.repository.AgencyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    public Integer save(AgencyVO vO) {
        Agency bean = new Agency();
        BeanUtils.copyProperties(vO, bean);
        bean = agencyRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        agencyRepository.deleteById(id);
    }

    public void update(Integer id, AgencyUpdateVO vO) {
        Agency bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        agencyRepository.save(bean);
    }

    public AgencyDTO getById(Integer id) {
        Agency original = requireOne(id);
        return toDTO(original);
    }

    public Page<AgencyDTO> query(AgencyQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private AgencyDTO toDTO(Agency original) {
        AgencyDTO bean = new AgencyDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Agency requireOne(Integer id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
