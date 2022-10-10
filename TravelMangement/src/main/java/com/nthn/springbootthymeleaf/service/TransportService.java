package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Transport;
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

    public Integer save(Transport transportDTO) {
        Transport transport = new Transport();
        BeanUtils.copyProperties(transportDTO, transport);
        transport = transportRepository.save(transport);
        return transport.getId();
    }

    public void delete(Integer id) {
        transportRepository.deleteById(id);
    }

    public void update(Integer id, Transport transportDTO) {
        Transport transport = requireOne(id);
        BeanUtils.copyProperties(transportDTO, transport);
        transportRepository.save(transport);
    }

    public Transport read(Integer id) {
        return requireOne(id);
    }


    private Transport requireOne(Integer id) {
        return transportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public TransportDTO getById(Integer id) {
//        Transport original = requireOne(id);
//        return toDTO(original);
//    }
//
//    public Page<TransportDTO> query(TransportQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private TransportDTO toDTO(Transport original) {
//        TransportDTO bean = new TransportDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
