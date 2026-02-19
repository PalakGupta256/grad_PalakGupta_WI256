package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // GET by regNo
    @GetMapping("/{regNo}")
    public Student getStudent(@PathVariable Long regNo) {
        return repository.findById(regNo).orElse(null);
    }

    // POST
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    // PUT
    @PutMapping("/{regNo}")
    public Student updateStudent(@PathVariable Long regNo, @RequestBody Student student) {
        student.setRegNo(regNo);
        return repository.save(student);
    }

    // PATCH
    @PatchMapping("/{regNo}")
    public Student patchStudent(@PathVariable Long regNo,
                                @RequestBody Map<String, Object> updates) {

        Student student = repository.findById(regNo).orElse(null);

        if (student != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        student.setName((String) value);
                        break;
                    case "school":
                        student.setSchool((String) value);
                        break;
                    case "percentage":
                        student.setPercentage(Double.valueOf(value.toString()));
                        break;
                    case "standard":
                        student.setStandard(Integer.valueOf(value.toString()));
                        break;
                    case "gender":
                        student.setGender((String) value);
                        break;
                }
            });

            return repository.save(student);
        }

        return null;
    }

    // DELETE
    @DeleteMapping("/{regNo}")
    public void deleteStudent(@PathVariable Long regNo) {
        repository.deleteById(regNo);
    }

    // GET by school
    @GetMapping("/school")
    public List<Student> getBySchool(@RequestParam String name) {
        return repository.findBySchool(name);
    }

    // GET school count
    @GetMapping("/school/count")
    public long countBySchool(@RequestParam String name) {
        return repository.countBySchool(name);
    }

    // GET standard count
    @GetMapping("/school/standard/count")
    public long countByStandard(@RequestParam("class") Integer standard) {
        return repository.countByStandard(standard);
    }

    // GET result
    @GetMapping("/result")
    public List<Student> getResult(@RequestParam boolean pass) {
        if (pass) {
            return repository
                    .findByPercentageGreaterThanEqualOrderByPercentageDesc(40.0);
        } else {
            return repository.findAll();
        }
    }

    // GET strength
    @GetMapping("/strength")
    public long getStrength(@RequestParam String gender,
                            @RequestParam Integer standard) {
        return repository.countByGenderAndStandard(gender, standard);
    }
}
