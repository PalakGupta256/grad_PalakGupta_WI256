package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // let Postgres auto-increment
    @Column(name = "roll_no")
    private Integer rollNo;  // use Integer, not int

    @Column(name = "name")
    private String name;

    @Column(name = "standard")
    private String standard;

    @Column(name = "school")
    private String school;

    public Student() {}

    public Student(String name, String standard, String school) {
        this.name = name;
        this.standard = standard;
        this.school = school;
    }

    // getters and setters
    public Integer getRollNo() { return rollNo; }
    public void setRollNo(Integer rollNo) { this.rollNo = rollNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStandard() { return standard; }
    public void setStandard(String standard) { this.standard = standard; }
    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
}
