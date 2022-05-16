package com.codingclub.springbootdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please fill Name!")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1, message = "Age must be greater than 0!")
    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private boolean sex;

    @NotBlank(message = "Address must be not null!")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Position must be not null!")
    @Column(name = "position")
    private String position;

    @NotBlank(message = "Phone number must be not null!")
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name= "start_day")
    private Date startDay;

    @Min(value = 1,message = "Money Per Hour must be greater than zero!")
    @Column(name = "money_per_hour")
    private Double moneyPerHour;

    @NotNull(message = "Team must not be null!")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id")
    private Team team;
    public int getId() {
        return id;
    }
    @OneToMany(mappedBy = "employee")
    private List<Image> images;

    @OneToMany(mappedBy = "employee")
    private List<Working> workings;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Double getMoneyPerHour() {
        return moneyPerHour;
    }

    public void setMoneyPerHour(Double moneyPerHour) {
        this.moneyPerHour = moneyPerHour;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", startDay=" + startDay +
                ", moneyPerHour=" + moneyPerHour +
                ", team=" + team +
                '}';
    }
}
