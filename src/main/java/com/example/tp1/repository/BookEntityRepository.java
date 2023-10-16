package com.example.tp1.repository;

import com.example.tp1.entity.BookEntity;
import com.example.tp1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
    public List<BookEntity> findAll();

    public BookEntity save(BookEntity book);

    public Optional<BookEntity> findByCode(String code);

    public List<BookEntity> findByStudentId(Integer studentId);

    public List<BookEntity> findByStudent(StudentEntity student);
}
