package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.DestinationDTO;
import com.nthn.springbootthymeleaf.VO.DestinationQueryVO;
import com.nthn.springbootthymeleaf.VO.DestinationUpdateVO;
import com.nthn.springbootthymeleaf.VO.DestinationVO;
import com.nthn.springbootthymeleaf.model.Destination;
import com.nthn.springbootthymeleaf.repository.DestinationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Integer save(DestinationVO vO) {
        Destination bean = new Destination();
        BeanUtils.copyProperties(vO, bean);
        bean = destinationRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        destinationRepository.deleteById(id);
    }

    public void update(Integer id, DestinationUpdateVO vO) {
        Destination bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        destinationRepository.save(bean);
    }

    public DestinationDTO getById(Integer id) {
        Destination original = requireOne(id);
        return toDTO(original);
    }

    public Page<DestinationDTO> query(DestinationQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DestinationDTO toDTO(Destination original) {
        DestinationDTO bean = new DestinationDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Destination requireOne(Integer id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
