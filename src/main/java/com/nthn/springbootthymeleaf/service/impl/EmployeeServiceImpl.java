package com.nthn.springbootthymeleaf.service.impl;


import com.nthn.springbootthymeleaf.entity.Employee;
import com.nthn.springbootthymeleaf.repository.EmployeeRepository;
import com.nthn.springbootthymeleaf.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Integer save(Employee employee) {
        Employee bean = new Employee();
        BeanUtils.copyProperties(employee, bean);
        bean = employeeRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    public void update(Integer id, Employee vO) {
        Employee bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        employeeRepository.save(bean);
    }

    public Employee getById(Integer id) {
        return (requireOne(id));
    }

//    public Page<EmployeeDTO> query(EmployeeQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }


    private Employee requireOne(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<EmployeeDTO> query(EmployeeQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private EmployeeDTO toDTO(Employee original) {
//        EmployeeDTO bean = new EmployeeDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
