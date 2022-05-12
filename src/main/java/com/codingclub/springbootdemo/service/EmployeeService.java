package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getListEmployee();
    public Employee saveEmployee(Employee employee);
    public Employee getEmployeeById(int id);

    public Employee converEmployeeJson(String employee);

    public Employee updateEmployee(Employee employee, int id);

    public void deleteEmployee(int id);

    public List<Employee> getListEmployeeByTeamId(int teamId);
}
