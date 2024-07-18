package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Customer;
import com.nthn.springbootthymeleaf.repository.AccountRepository;
import com.nthn.springbootthymeleaf.repository.CustomerRepository;
import com.nthn.springbootthymeleaf.service.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public Customer update(Integer id, Customer customer) {
        Customer customer1 = customerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(customer, customer1, "account");
        return customerRepository.save(customer1);
    }
    
    @Override
    public Customer save(@NotNull Customer customer) {
        if (customer.getId() != null) {
            System.out.println(customer.getId());
            Customer customer1 = customerRepository.findById(customer.getId()).get();
            BeanUtils.copyProperties(customer, customer1, "account");
            return customerRepository.save(customer1);
        }
        System.out.println((Object) null);
        
        return customerRepository.save(customer);
    }
    
    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }
    
    @Override
    public Customer update(String identity, Customer customer) {
        return customerRepository.save(customer);
    }
    
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
    
    
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    
    
    @Override
    public Customer getCustomerByAccount(Integer id) {
        return customerRepository.findByAccountId(id);
    }
    
    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).get();
    }
    
    
    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
    
    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    private Customer requireOne(Integer id) {
        return customerRepository.findById(id)
                                 .orElseThrow(() -> new NoSuchElementException("Customer not found: " + id));
    }
    
}
