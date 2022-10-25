package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Employee;

public interface EmployeeService {
    Integer save(Employee employee);

    void delete(Integer id);

    void update(Integer id, Employee vO);

    Employee getById(Integer id);
}
