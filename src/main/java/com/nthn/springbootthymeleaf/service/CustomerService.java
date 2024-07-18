package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer update(Integer id, Customer customer);
    Customer save(Customer customer);

    Customer add(Customer customer);

    Customer update(String identity, Customer customer);

    void delete(Integer id);


    List<Customer> getCustomers();

    Customer getCustomerByAccount(Integer id);

    Customer getCustomerById(Integer id);

    Customer getCustomerByPhone(String phone);


    Customer addCustomer(Customer customer);
}
