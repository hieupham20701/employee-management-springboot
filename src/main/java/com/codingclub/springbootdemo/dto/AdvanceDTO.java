package com.codingclub.springbootdemo.dto;

import java.util.Date;

public class AdvanceDTO {
    private int id;
    private Date date;
    private Double money;
    private EmployeeDTO employeeDTO;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    @Override
    public String toString() {
        return "AdvanceDTO{" +
                "id=" + id +
                ", date=" + date +
                ", money=" + money +
                ", employeeDTO=" + employeeDTO +
                '}';
    }
}
