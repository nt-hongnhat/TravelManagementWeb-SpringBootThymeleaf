package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.WardDTO;
import com.nthn.springbootthymeleaf.VO.WardQueryVO;
import com.nthn.springbootthymeleaf.VO.WardUpdateVO;
import com.nthn.springbootthymeleaf.VO.WardVO;
import com.nthn.springbootthymeleaf.model.Ward;
import com.nthn.springbootthymeleaf.repository.WardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WardService {

    @Autowired
    private WardRepository wardRepository;

    public Integer save(WardVO vO) {
        Ward bean = new Ward();
        BeanUtils.copyProperties(vO, bean);
        bean = wardRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        wardRepository.deleteById(id);
    }

    public void update(String id, WardUpdateVO vO) {
        Ward bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        wardRepository.save(bean);
    }

    public WardDTO getById(String id) {
        Ward original = requireOne(id);
        return toDTO(original);
    }

    public Page<WardDTO> query(WardQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private WardDTO toDTO(Ward original) {
        WardDTO bean = new WardDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Ward requireOne(String id) {
        return wardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public Object getAll() {
        return null;
    }
}
