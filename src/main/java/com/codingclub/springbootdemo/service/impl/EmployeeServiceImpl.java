package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getListEmployee() {


        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id);

    }

    @Override
    public Employee converEmployeeJson(String employee) {
        Employee employee1 = new Employee();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            employee1 = objectMapper.readValue(employee,Employee.class);

        }catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return employee1;
    }
}
