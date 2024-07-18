package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();
    
    Payment findById(int id);
    
    Payment findByMethod(String method);
}
