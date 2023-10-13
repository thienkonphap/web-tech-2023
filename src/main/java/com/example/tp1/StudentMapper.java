package com.example.tp1;

import java.util.List;

public class StudentMapper {
    public static Student toStudent(Student studentEntity) {
        return Student.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .email(studentEntity.getEmail())
                .age(studentEntity.getAge())
                .build();
    }
    public static List<Student> toStudents(List<Student> studentEntities) {
        return studentEntities.stream().map(StudentMapper::toStudent).toList();
    }
    public static Student toStudentEntity(Student model) {
        return Student.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .email(model.getEmail())
                .age(model.getAge())
                .build();
    }
}

