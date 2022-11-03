package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    Customer add(String fullName, String identified, String phone, String address);

    void delete(Integer id);

    void update(Integer id, Customer customer);

    Customer getById(Integer id);

    Customer getCustomer(String identified);

    List<Customer> getCustomers();

    Page<Customer> getCustomers(Pageable pageable);

    long countAllByNationality(String nationality);
    

    Customer getCustomerByAccount(Integer id);
}
