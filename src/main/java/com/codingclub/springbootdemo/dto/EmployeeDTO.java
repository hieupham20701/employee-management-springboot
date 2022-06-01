package com.codingclub.springbootdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.PastOrPresent;
import java.util.Date;

public class EmployeeDTO {

    private int id;
    private String fullName;
    private int age;
    private boolean sex;
    private String address;
    private String position;
    private String phoneNumber;
    private Date startDay;
    private Double moneyPerHour;
    private TeamDTO teamDTO;

    private ImageDTO imageDTO;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public TeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", startDay=" + startDay +
                ", moneyPerHour=" + moneyPerHour +
                ", teamDTO=" + teamDTO +
                ", imageDTO=" + imageDTO +
                '}';
    }
}
