package com.example.tp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentEntityRepository studentEntityRepository;

    public List<Student> findAll() {
        return studentEntityRepository.findAll();
    }
    public Optional<Student> findById(Integer id) {
        return studentEntityRepository.findById(id);
    }
    public Student findByEmail(String email) {
        return studentEntityRepository.findByEmail(email);
    }
    public List<Student> findByFirstName(String firstname) {
        return studentEntityRepository.findByFirstName(firstname);
    }
    public List<Student> findByAgeGreaterThan20() {
        return studentEntityRepository.findByAgeGreaterThan20();
    }
    public Student saveStudent(Student student) {
        return studentEntityRepository.save(student);
    }

    public Student save(Student student) {
        return studentEntityRepository.save(student);
    }
    public void saveAll(List<Student> student) {
        studentEntityRepository.saveAll(student);
    }
    public void deleteById(Integer id) {
        studentEntityRepository.deleteById(id);
    }
}
