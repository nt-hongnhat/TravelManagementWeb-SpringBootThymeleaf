package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.Customer;
import com.nthn.springbootthymeleaf.repository.CustomerRepository;
import com.nthn.springbootthymeleaf.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer add(String fullName, String identified, String phone, String address) {
        Customer customer = new Customer();
        customer.setFullname(fullName);
        customer.setIdentified(identified);
        customer.setPhone(phone);
        customer.setAddress(address);
        return this.save(customer);
    }

    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    public void update(Integer id, Customer customerDTO) {
        Customer customer = requireOne(id);
        BeanUtils.copyProperties(customerDTO, customer);
        customerRepository.save(customer);
    }

    public Customer getById(Integer id) {
        return requireOne(id);
    }

    @Override
    public Customer getCustomer(String identified) {
        return customerRepository.getCustomerByIdentified(identified);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public long countAllByNationality(String nationality) {
        return customerRepository.countAllByNationality(nationality);
    }

    @Override
    public Customer getCustomerByAccount(Integer id) {
        return customerRepository.findByAccountId(id);
    }

    private Customer requireOne(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found: " + id));
    }

}
