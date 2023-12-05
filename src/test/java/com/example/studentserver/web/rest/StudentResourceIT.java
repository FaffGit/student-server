package com.example.studentserver.web.rest;

import com.example.studentserver.domain.Student;
import com.example.studentserver.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class StudentResourceIT {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    void createStudent() {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        Student student = new Student();
        student.setName("Pierre");
        studentRepository.save(student);

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
    }

    @Test
    @Transactional
    void getStudentById() {
        Student student = new Student();
        student.setName("Paul");
        student = studentRepository.save(student);

        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getName()).isEqualTo("Paul");
    }

    @Test
    @Transactional
    void getAllStudents() {
        List<Student> initialStudentList = studentRepository.findAll();

        Student student1 = new Student();
        student1.setName("Alice");
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Bob");
        studentRepository.save(student2);

        List<Student> updatedStudentList = studentRepository.findAll();
        assertThat(updatedStudentList).hasSize(initialStudentList.size() + 2);
    }

    @Test
    @Transactional
    void deleteStudent() {
        Student student = new Student();
        student.setName("John");
        student = studentRepository.save(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();
        assertThat(databaseSizeBeforeDelete).isGreaterThan(0);

        studentRepository.deleteById(student.getId());

        List<Student> updatedStudentList = studentRepository.findAll();
        assertThat(updatedStudentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
