package com.example.tp1;

import com.example.tp1.domain.Student;
import com.example.tp1.entity.StudentEntity;
import com.example.tp1.mapper.StudentMapper;
import com.example.tp1.repository.StudentEntityRepository;
import com.example.tp1.services.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private StudentEntityRepository repository;

    private StudentService tested;

    @BeforeEach
    void setUp() {
        tested = new StudentService(repository);
    }

    @Test
    void should_get_student_by_id() throws Exception {
        // GIVEN
        UUID id = UUID.randomUUID();
        StudentEntity studentEntity = new StudentEntity(id, "David", "Klezt", "david@gmail.com", 23, null);
        Optional<StudentEntity> optionalStudent = Optional.of(studentEntity);
        Mockito.when(repository.findById(id)).thenReturn(optionalStudent);
        Student expected = StudentMapper.toStudent(studentEntity);
        // WHEN
        Optional<StudentEntity> actual = tested.findById(id);
        Student res = StudentMapper.toStudent(actual.get());
        // THEN
        Assertions.assertThat(res).isEqualTo(expected);
    }

    @Test
    void should_get_student_by_email() throws Exception {
        // GIVEN
        String email = "david@gmail.com";
        StudentEntity studentEntity = new StudentEntity(UUID.randomUUID(), "David", "Klezt", email, 23, null);
        Optional<StudentEntity> optionalStudent = Optional.of(studentEntity);
        Mockito.when(repository.findByEmail(email)).thenReturn(optionalStudent);
        Student expected = StudentMapper.toStudent(studentEntity);
        // WHEN
        Optional<StudentEntity> actual = tested.findByEmail(email);
        Student res = StudentMapper.toStudent(actual.get());
        // THEN
        Assertions.assertThat(res).isEqualTo(expected);
    }
}
