package com.example.tp1.services;

import com.example.tp1.domain.Student;
import com.example.tp1.entity.StudentEntity;
import com.example.tp1.mapper.StudentMapper;
import com.example.tp1.repository.StudentEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentEntityRepository studentEntityRepository;

    public List<StudentEntity> findAll() {
        List<StudentEntity> studentsList = studentEntityRepository.findAll();
        return studentsList;
    }

    public Optional<StudentEntity>  findById(UUID id) {
        return studentEntityRepository.findById(id);
    }

    public Optional<StudentEntity> findByEmail(String email) {
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
