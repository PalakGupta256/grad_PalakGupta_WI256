package com.example.demo.repository.h2;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentH2Repository extends JpaRepository<Student, Integer> { }
