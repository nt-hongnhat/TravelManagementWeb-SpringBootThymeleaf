package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Payment;
import com.nthn.springbootthymeleaf.repository.PaymentRepository;
import com.nthn.springbootthymeleaf.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
    
    @Override
    public Payment findById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }
    
    @Override
    public Payment findByMethod(String method) {
        return paymentRepository.findByMethod(method);
    }
}
