package com.example.tp1.controllers;

import com.example.tp1.Student;
import com.example.tp1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Student findByEmail(@PathVariable("email") String email) {
        return studentService.findByEmail(email);
    }
    @GetMapping("/firstname/{firstname}")
    public List<Student> findByFirstName(@PathVariable("firstname") String firstname) {
        return studentService.findByFirstName(firstname);
    }
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
    @GetMapping("/olderthan20")
    public List<Student> findByAgeGreaterThan20() {
        return studentService.findByAgeGreaterThan20();
    }
    @PutMapping()
    public Student updateStudentByEmail(@RequestBody Student student, @RequestParam String email) {
        Optional<Student> foundStudent = studentService.findById(student.getId());
        foundStudent.ifPresent(value -> value.setEmail(email));
        return studentService.save(foundStudent.orElse(null));
    }
    @PutMapping("/incrementage")
    public void incrementAge() {
        List<Student> foundStudent = studentService.findAll();
        foundStudent.forEach(student -> student.setAge(student.getAge() + 1));
        studentService.saveAll(foundStudent);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Integer id) {
        Optional <Student> foundStudent = studentService.findById(id);
        foundStudent.ifPresent(value -> studentService.deleteById(value.getId()));
    }
}
