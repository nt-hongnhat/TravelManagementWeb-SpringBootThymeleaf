package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Employee;

import javax.validation.constraints.NotNull;

public interface EmployeeService {
    @NotNull Integer save(Employee employee);

    void delete(Integer id);

    void update(Integer id, Employee vO);

    Employee getById(Integer id);
}
