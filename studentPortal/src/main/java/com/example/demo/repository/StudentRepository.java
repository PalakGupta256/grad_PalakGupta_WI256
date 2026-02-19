package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySchool(String school);

    long countBySchool(String school);

    long countByStandard(Integer standard);

    List<Student> findByPercentageGreaterThanEqualOrderByPercentageDesc(Double percentage);

    long countByGenderAndStandard(String gender, Integer standard);
}
