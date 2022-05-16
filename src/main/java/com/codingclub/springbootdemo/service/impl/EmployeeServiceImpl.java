package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.repository.TeamRepository;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public List<Employee> getListEmployee() {


        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setStartDay(new Timestamp(System.currentTimeMillis()));
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

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        Employee oldEmployee = employeeRepository.findById(id).get();
//        System.out.println(employee.toString());
        System.out.println(oldEmployee.toString());
        if (employee.getStartDay() != null){

            oldEmployee.setStartDay(employee.getStartDay());
        }
        if(employee.getPosition() != null){
            oldEmployee.setPosition(employee.getPosition());
        }
        if(employee.getPhoneNumber() != null){
            oldEmployee.setPhoneNumber(employee.getPhoneNumber());
        }
        if(employee.getTeam() != null){
            oldEmployee.setTeam(teamRepository.getById(employee.getTeam().getId()));
        }
        if(employee.getMoneyPerHour() != null){
            oldEmployee.setMoneyPerHour(employee.getMoneyPerHour());
        }
        if(employee.getFullName() != null){
            oldEmployee.setFullName(employee.getFullName());
        }
        if(employee.getAge() != 0){
            oldEmployee.setAge(employee.getAge());
        }
        if(!employee.getAddress().equals("")){
            oldEmployee.setAddress(employee.getAddress());
        }
        oldEmployee.setSex(employee.isSex());
        return employeeRepository.save(oldEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.delete(employeeRepository.findById(id).get());
    }

    @Override
    public List<Employee> getListEmployeeByTeamId(int teamId) {
        return employeeRepository.findEmployeeByTeam_IdOrderByFullNameAsc(teamId);
    }

    @Override
    public void deleteEmployees(int[] id) {
        for(int i=0;i<id.length;i++){
            System.out.println(id[i]);
            deleteEmployee(id[i]);
        }
    }
}
