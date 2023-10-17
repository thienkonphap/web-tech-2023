package com.example.tp1.mapper;

import com.example.tp1.domain.Student;
import com.example.tp1.entity.StudentEntity;

import java.util.List;

public class StudentMapper {
    public static Student toStudent(StudentEntity studentEntity) {
        return Student.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .email(studentEntity.getEmail())
                .age(studentEntity.getAge())
                .build();
    }

    public static List<Student> toStudents(List<StudentEntity> studentEntities) {
        return studentEntities.stream().map(StudentMapper::toStudent).toList();
    }

    public static StudentEntity toStudentEntity(StudentEntity model) {
        return StudentEntity.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .email(model.getEmail())
                .age(model.getAge())
                .build();
    }
}

