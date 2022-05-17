package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findEmployeeById(int id);
    public List<Employee> findEmployeeByTeam_IdOrderByFullNameAsc(int id);
    Page<Employee> findAll(Pageable pageable);
}
