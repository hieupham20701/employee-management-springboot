package com.codingclub.springbootdemo.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "file")
    private byte[] file;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "file_name")
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public int getId() {
        return id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", file=" + Arrays.toString(file) +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", employee=" + employee +
                '}';
    }
}
