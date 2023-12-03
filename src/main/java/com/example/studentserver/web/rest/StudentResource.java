package com.example.studentserver.web.rest;

import com.example.studentserver.service.StudentService;
import com.example.studentserver.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {

    public final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public List<Student> getAllItem() {
        return studentService.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getItem(@PathVariable String id) {
        Student student1 = new Student();
        student1.setName("Pierre");
        return student1;
    }
}