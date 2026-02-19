package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showForm() {
        return "student";
    }

    @PostMapping("/insert")
    public String insertStudent(@RequestParam String name,
                                @RequestParam String standard,
                                @RequestParam String school) {
        Student student = new Student(name, standard, school);
        service.insertStudent(student);
        return "success";
    }

}
