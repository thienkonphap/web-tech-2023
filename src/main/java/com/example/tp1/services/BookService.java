package com.example.tp1.services;

import com.example.tp1.entity.StudentEntity;
import com.example.tp1.repository.BookEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.tp1.entity.BookEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {
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

    public void saveAll(List<BookEntity> books) {
        bookEntityRepository.saveAll(books);
    }

    public List<BookEntity> findByStudent(StudentEntity student) {
        return bookEntityRepository.findByStudent(student);
    }

    public Optional<BookEntity> findByTile(String title) {
        return bookEntityRepository.findByTitle(title);
    }

    public List<BookEntity> findByStudentId(UUID studentId) {
        return bookEntityRepository.findByStudentId(studentId);
    }
}
