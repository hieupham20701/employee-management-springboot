package com.codingclub.springbootdemo.service;


import com.codingclub.springbootdemo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public List<Employee> getListEmployee();
    public Employee saveEmployee(Employee employee);
    public Employee getEmployeeById(int id);

    public Employee converEmployeeJson(String employee);

    public Employee updateEmployee(Employee employee, int id);

    public void deleteEmployee(int id);

    public List<Employee> getListEmployeeByTeamId(int teamId);

    public void deleteEmployees(int[] id);

    public Map<String, Object> getAllEmployeePage(int page, int size);

}
