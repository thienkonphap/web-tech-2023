package com.example.tp1.controllers;

import com.example.tp1.domain.Student;
import com.example.tp1.entity.StudentEntity;
import com.example.tp1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/email/{email}")
    public StudentEntity findByEmail(@PathVariable("email") String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping("/firstname/{firstname}")
    public List<StudentEntity> findByFirstName(@PathVariable("firstname") String firstname) {
        return studentService.findByFirstName(firstname);
    }

    @PostMapping
    public StudentEntity saveStudent(@RequestBody StudentEntity student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/olderthan20")
    public List<StudentEntity> findByAgeGreaterThan20() {
        return studentService.findByAgeGreaterThan20();
    }

    @PutMapping()
    public StudentEntity updateStudentByEmail(@RequestBody StudentEntity student, @RequestParam String email) {
        Optional<StudentEntity> foundStudent = studentService.findById(student.getId());
        foundStudent.ifPresent(value -> value.setEmail(email));
        return studentService.save(foundStudent.orElse(null));
    }

    @PutMapping("/incrementage")
    public void incrementAge() {
        List<StudentEntity> foundStudent = studentService.findAll();
        foundStudent.forEach(student -> student.setAge(student.getAge() + 1));
        studentService.saveAll(foundStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") UUID id) {
        Optional<StudentEntity> foundStudent = studentService.findById(id);
        foundStudent.ifPresent(value -> studentService.deleteById(value.getId()));
    }
}
