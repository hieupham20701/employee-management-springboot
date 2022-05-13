package com.codingclub.springbootdemo.dto;

import java.io.Serializable;
import java.util.Objects;

public class WorkingDTO implements Serializable {
    private  int id;
    private  String date;

    private  Integer hour;
    private EmployeeDTO employeeDTO;
    public WorkingDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }


    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDTO entity = (WorkingDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.hour, entity.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, hour);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "date = " + date + ", " +
                "hour = " + hour + ", " ;
    }
}
