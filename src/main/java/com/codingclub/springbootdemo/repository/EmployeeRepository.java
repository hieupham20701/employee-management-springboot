package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findEmployeeById(int id);
}
