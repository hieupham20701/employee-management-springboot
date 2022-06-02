package com.codingclub.springbootdemo.service;


import com.codingclub.springbootdemo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getListEmployee();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(int id);

    Employee converEmployeeJson(String employee);

    Employee updateEmployee(Employee employee, int id);

    void deleteEmployee(int id);

    List<Employee> getListEmployeeByTeamId(int teamId);

    void deleteEmployees(int[] id);

    Map<String, Object> getAllEmployeePage(int page, int size);

    Map<String, Object> getEmployeeByNameSearch(int page, int size, String name);


}
