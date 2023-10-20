package com.example.tp1.repository;

import com.example.tp1.entity.BookEntity;
import com.example.tp1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    public List<BookEntity> findAll();

    public BookEntity save(BookEntity book);

    public Optional<BookEntity> findByCode(String code);

    public List<BookEntity> findByStudent(StudentEntity student);

    public List<BookEntity> findByStudentId(UUID studentId);

    public Optional<BookEntity> findByTitle(String title);
}
