package com.example.tp1.services;

import com.example.tp1.entity.StudentEntity;
import com.example.tp1.repository.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tp1.entity.BookEntity;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookEntityRepository bookEntityRepository;

    public List<BookEntity> findAll() {
        List<BookEntity> booksList = bookEntityRepository.findAll();
        return booksList;
    }

    public Optional<BookEntity> findByBookCode(String code) {
        return bookEntityRepository.findByCode(code);
    }

    public BookEntity save(BookEntity book) {
        return bookEntityRepository.save(book);
    }

    public List<BookEntity> findByStudentId(Integer studentId) {
        return bookEntityRepository.findByStudentId(studentId);
    }

    public void saveAll(List<BookEntity> books) {
        bookEntityRepository.saveAll(books);
    }

    public List<BookEntity> findByStudent(StudentEntity student) {
        return bookEntityRepository.findByStudent(student);
    }
}
