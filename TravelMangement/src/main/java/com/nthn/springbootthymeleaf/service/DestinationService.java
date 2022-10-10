package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Destination;
import com.nthn.springbootthymeleaf.repository.DestinationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;


    public List<Destination> getDestinations(String keyword) {
        if (keyword == null) {
            return destinationRepository.findAll(Sort.by("name").ascending());
        } else return destinationRepository.findAll(Sort.by("id").ascending());
    }

    public Integer save(Destination destination) {
        Destination bean = new Destination();
        BeanUtils.copyProperties(destination, bean);
        bean = destinationRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        destinationRepository.deleteById(id);
    }

    public void update(Integer id, Destination destination) {
        Destination bean = requireOne(id);
        BeanUtils.copyProperties(destination, bean);
        destinationRepository.save(bean);
    }

    public Destination getById(Integer id) {
        return requireOne(id);
    }

//    public Page<DestinationDTO> query(DestinationQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }


    private Destination requireOne(Integer id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<DestinationDTO> query(DestinationQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private DestinationDTO toDTO(Destination original) {
//        DestinationDTO bean = new DestinationDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
