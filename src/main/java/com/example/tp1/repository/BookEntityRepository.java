package com.example.tp1.repository;

import com.example.tp1.Book;
import com.example.tp1.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookEntityRepository extends JpaRepository<Book,Integer> {
    public List<Book> findAll();
    public Book save(Book book);
    public Optional<Book> findByCode(String code);
    public List<Book> findByStudentId(Integer studentId);
    public List<Book> findByStudent(Student student);
}
