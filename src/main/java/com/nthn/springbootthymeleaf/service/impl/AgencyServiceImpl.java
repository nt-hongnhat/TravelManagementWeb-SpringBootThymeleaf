package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Agency;
import com.nthn.springbootthymeleaf.repository.AgencyRepository;
import com.nthn.springbootthymeleaf.service.AgencyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    // CREATE
    public Agency create(Agency agency) {
        return agencyRepository.save(agency);
    }

    // READ
    public Agency read(Integer id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        return agency.orElse(null);
    }

    public List<Agency> read(String keyword) {
        if (keyword == null)
            return agencyRepository.findAll();
        return agencyRepository.findByNameContainingIgnoreCaseOrderByName(keyword);
    }

//    public List<Agency> getAgencyPage(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return agencyRepository.findAll(pageable).getContent();
//    }

    // UPDATE
    public Agency update(Integer id, Agency agency) {
        Agency original = this.read(id);
        BeanUtils.copyProperties(agency, original);
        return agencyRepository.save(original);
    }

    // DELETE
    public void delete(Integer id) {
        agencyRepository.deleteById(id);
    }


}
