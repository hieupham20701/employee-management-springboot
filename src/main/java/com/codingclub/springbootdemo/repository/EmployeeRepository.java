package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(int id);
    List<Employee> findEmployeeByTeam_IdOrderByFullNameAsc(int id);
    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findEmployeeByFullNameContaining(String nameSearch, Pageable pageable);


}
