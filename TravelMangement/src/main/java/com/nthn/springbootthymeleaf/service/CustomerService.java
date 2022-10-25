package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Integer save(Customer customerDTO);

    void delete(Integer id);

    void update(Integer id, Customer customerDTO);

    Customer getById(Integer id);

    List<Customer> getCustomers();

    Page<Customer> getCustomers(Pageable pageable);

    long countAllByNationality(String nationality);
}
