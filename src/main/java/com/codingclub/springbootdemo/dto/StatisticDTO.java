package com.codingclub.springbootdemo.dto;

public class StatisticDTO {

    private EmployeeDTO employeeDTO;
    private Integer numOfWorkingDays;
    private Double totalGet;
    private Double totalAdvance;

    private Double summary;

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public Integer getNumOfWorkingDays() {
        return numOfWorkingDays;
    }

    public void setNumOfWorkingDays(Integer numOfWorkingDays) {
        this.numOfWorkingDays = numOfWorkingDays;
    }

    public Double getTotalGet() {
        return totalGet;
    }

    public void setTotalGet(Double totalGet) {
        this.totalGet = totalGet;
    }

    public Double getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(Double totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public Double getSummary() {
        return summary;
    }

    public void setSummary(Double summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "StatisticDTO{" +
                "numOfWorkingDays=" + numOfWorkingDays +
                ", totalGet=" + totalGet +
                ", totalAdvance=" + totalAdvance +
                '}';
    }

}
