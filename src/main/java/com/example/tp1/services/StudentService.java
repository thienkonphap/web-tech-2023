package com.example.tp1.services;

import com.example.tp1.domain.Student;
import com.example.tp1.entity.StudentEntity;
import com.example.tp1.mapper.StudentMapper;
import com.example.tp1.repository.StudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentEntityRepository studentEntityRepository;

    public List<StudentEntity> findAll() {
        List<StudentEntity> studentsList = studentEntityRepository.findAll();
        return StudentMapper.toStudents(studentsList);
    }

    public Student findById(UUID id) throws Exception {
        Optional<StudentEntity> studentEntityOptional = studentEntityRepository.findById(id);
        StudentEntity studentEntity = studentEntityOptional.orElseThrow(() -> new Exception("Student not found"));
        return StudentMapper.toStudent(studentEntity);
    }

    public StudentEntity findByEmail(String email) {
        return studentEntityRepository.findByEmail(email);
    }

    public List<StudentEntity> findByFirstName(String firstname) {
        return studentEntityRepository.findByFirstName(firstname);
    }

    public List<StudentEntity> findByAgeGreaterThan20() {
        return studentEntityRepository.findByAgeGreaterThan20();
    }

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentEntityRepository.save(studentEntity);
    }

    public StudentEntity save(StudentEntity studentEntity) {
        return studentEntityRepository.save(studentEntity);
    }

    public void saveAll(List<StudentEntity> studentEntity) {
        studentEntityRepository.saveAll(studentEntity);
    }

    public void deleteById(UUID id) {
        studentEntityRepository.deleteById(id);
    }
}
