package com.example.tp1.controllers;

import com.example.tp1.entity.StudentEntity;
import com.example.tp1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @GetMapping
    public List<StudentEntity> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/email/{abcd}")
    public Optional<StudentEntity> findByEmail(@PathVariable("abcd") String email) {
        return studentService.findByEmail(email);
    }

    @PostMapping("/firstname/{firstname}/{email}")
    public Optional<StudentEntity> updateFirstName(@PathVariable("firstname") String firstname, @PathVariable("email") String email) {
        Optional<StudentEntity> foundStudent = findByEmail(email);
        foundStudent.ifPresent(student -> student.setFirstName(firstname));
        studentService.save(foundStudent.orElse(null));
        return foundStudent;
    }

    @GetMapping("/id/{id}")
    public Optional<StudentEntity> findById(@PathVariable UUID id) {
        return studentService.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping
    public StudentEntity saveStudent(@RequestBody StudentEntity student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/olderthan20")
    public List<StudentEntity> findByAgeGreaterThan20() {
        return studentService.findByAgeGreaterThan20();
    }

    @PutMapping("/update")
    public StudentEntity updateStudentEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEMail) {
        Optional<StudentEntity> foundStudent = studentService.findByEmail(oldEmail);
        foundStudent.ifPresent(student -> student.setEmail(newEMail));
        return studentService.saveStudent(foundStudent.orElse(null));
    }

    @PutMapping("/incrementage")
    public void incrementAge() {
        List<StudentEntity> foundStudent = studentService.findAll();
        foundStudent.forEach(student -> student.setAge(student.getAge() + 1));
        studentService.saveAll(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") UUID id) {
        Optional<StudentEntity> foundStudent = studentService.findById(id);
        if (foundStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            studentService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
