package com.example.studentserver.web.rest;

import com.example.studentserver.service.StudentService;
import com.example.studentserver.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentResource {

    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.findById(id).orElse(null);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
        Student existingStudent = studentService.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAge(updatedStudent.getAge());
            return studentService.update(existingStudent);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);
    }
}
