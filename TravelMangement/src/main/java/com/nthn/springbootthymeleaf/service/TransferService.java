package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.TransferDTO;
import com.nthn.springbootthymeleaf.VO.TransferQueryVO;
import com.nthn.springbootthymeleaf.VO.TransferUpdateVO;
import com.nthn.springbootthymeleaf.VO.TransferVO;
import com.nthn.springbootthymeleaf.model.Transfer;
import com.nthn.springbootthymeleaf.repository.TransferRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public Integer save(TransferVO vO) {
        Transfer bean = new Transfer();
        BeanUtils.copyProperties(vO, bean);
        bean = transferRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        transferRepository.deleteById(id);
    }

    public void update(Integer id, TransferUpdateVO vO) {
        Transfer bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        transferRepository.save(bean);
    }

    public TransferDTO getById(Integer id) {
        Transfer original = requireOne(id);
        return toDTO(original);
    }

    public Page<TransferDTO> query(TransferQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TransferDTO toDTO(Transfer original) {
        TransferDTO bean = new TransferDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Transfer requireOne(Integer id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
