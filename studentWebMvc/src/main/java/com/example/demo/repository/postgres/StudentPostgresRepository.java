package com.example.demo.repository.postgres;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPostgresRepository extends JpaRepository<Student, Integer> { }
