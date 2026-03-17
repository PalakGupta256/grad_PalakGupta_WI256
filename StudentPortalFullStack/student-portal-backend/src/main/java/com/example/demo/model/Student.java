package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Long regNo;

    private Integer rollNo;
    private String name;
    private Integer standard;
    private String school;
    private String gender;
    private Double percentage;

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(Long regNo, Integer rollNo, String name, Integer standard,
                   String school, String gender, Double percentage) {
        this.regNo = regNo;
        this.rollNo = rollNo;
        this.name = name;
        this.standard = standard;
        this.school = school;
        this.gender = gender;
        this.percentage = percentage;
    }

    // Getters and Setters

    public Long getRegNo() {
        return regNo;
    }

    public void setRegNo(Long regNo) {
        this.regNo = regNo;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
