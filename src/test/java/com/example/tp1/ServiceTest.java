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

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private StudentEntityRepository repository;

    private StudentService tested;

    @BeforeEach
    void setUp() {
        // tested = new StudentService(repository);
    }

    @Test
    void should_get_student_by_id() throws Exception {
        // given
        UUID id = UUID.randomUUID();
        StudentEntity studentEntity = new StudentEntity(id, "David", "Klezt", "david@gmail.com", 23, null);
        Optional<StudentEntity> optionalStudent = Optional.of(studentEntity);
        Mockito.when(repository.findById(id)).thenReturn(optionalStudent);
        Student expected = StudentMapper.toStudent(studentEntity);

        // when
        // Student actual = tested.findById(id);
        // then
        // Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_throw_exception_when_get_student_by_id_not_found() {
        // given
        UUID id = UUID.randomUUID();
        Optional<StudentEntity> optionalStudent = Optional.empty();
        Mockito.when(repository.findById(id)).thenReturn(optionalStudent);
        //Student expected = StudentMapper.toStudent(studentEntity);

        // when
        // then
        assertThrows(Exception.class,
                () -> tested.findById(id));
    }
}
