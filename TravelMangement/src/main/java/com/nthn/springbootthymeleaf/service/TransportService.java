package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.TransportDTO;
import com.nthn.springbootthymeleaf.VO.TransportQueryVO;
import com.nthn.springbootthymeleaf.VO.TransportUpdateVO;
import com.nthn.springbootthymeleaf.VO.TransportVO;
import com.nthn.springbootthymeleaf.model.Transport;
import com.nthn.springbootthymeleaf.repository.TransportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public Integer save(TransportVO vO) {
        Transport bean = new Transport();
        BeanUtils.copyProperties(vO, bean);
        bean = transportRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        transportRepository.deleteById(id);
    }

    public void update(Integer id, TransportUpdateVO vO) {
        Transport bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        transportRepository.save(bean);
    }

    public TransportDTO getById(Integer id) {
        Transport original = requireOne(id);
        return toDTO(original);
    }

    public Page<TransportDTO> query(TransportQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TransportDTO toDTO(Transport original) {
        TransportDTO bean = new TransportDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Transport requireOne(Integer id) {
        return transportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
