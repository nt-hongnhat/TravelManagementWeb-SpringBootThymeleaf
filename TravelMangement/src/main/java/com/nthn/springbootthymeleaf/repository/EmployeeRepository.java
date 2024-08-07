package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,
		JpaSpecificationExecutor<Employee> {
	
}