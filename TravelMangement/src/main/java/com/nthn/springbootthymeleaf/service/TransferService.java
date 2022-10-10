package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Transfer;
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

    public Integer save(Transfer transfer) {
        Transfer bean = new Transfer();
        BeanUtils.copyProperties(transfer, bean);
        bean = transferRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        transferRepository.deleteById(id);
    }

    public void update(Integer id, Transfer transfer) {
        Transfer bean = requireOne(id);
        BeanUtils.copyProperties(transfer, bean);
        transferRepository.save(bean);
    }

    public Transfer getById(Integer id) {
        return (requireOne(id));
    }

//    public Page<TransferDTO> query(TransferQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }


    private Transfer requireOne(Integer id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<TransferDTO> query(TransferQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private TransferDTO toDTO(Transfer original) {
//        TransferDTO bean = new TransferDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
