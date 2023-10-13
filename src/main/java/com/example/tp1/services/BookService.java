package com.example.tp1.services;

import com.example.tp1.Student;
import com.example.tp1.repository.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tp1.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookEntityRepository bookEntityRepository;

    public List<Book> findAll() {
        List<Book> booksList = bookEntityRepository.findAll();
        return booksList;
    }
    public Optional<Book> findByBookCode(String code) {
        return bookEntityRepository.findByCode(code);
    }
    public Book save(Book book) {
        return bookEntityRepository.save(book);
    }
    public List<Book> findByStudentId(Integer studentId) {
        return bookEntityRepository.findByStudentId(studentId);
    }
    public void saveAll(List<Book> books) {
        bookEntityRepository.saveAll(books);
    }
    public List<Book> findByStudent(Student student) {
        return bookEntityRepository.findByStudent(student);
    }
}
