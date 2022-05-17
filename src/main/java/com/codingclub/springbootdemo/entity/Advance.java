package com.codingclub.springbootdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "advances")
public class Advance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private Date date;
    @Min(value = 1, message = "Money must be greater than zero!")
    @Column(name = "money")
    private Double money;

    @NotNull(message = "Employee must not be null")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Advance{" +
                "id=" + id +
                ", date=" + date +
                ", money=" + money +
                ", employee=" + employee +
                '}';
    }
}
