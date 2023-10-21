package com.example.tp1.controllers;

import com.example.tp1.entity.BookEntity;
import com.example.tp1.entity.StudentEntity;
import com.example.tp1.services.BookService;
import com.example.tp1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Iterable<BookEntity> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    public BookEntity saveBook(@RequestBody BookEntity book) {
        return bookService.save(book);
    }

    @GetMapping("/students/{studentId}")
    public List<BookEntity> findByStudentId(@PathVariable UUID studentId) {
        return bookService.findByStudentId(studentId);
    }

    /*
    * Rent books API
    * 1 - Get student by id
    * 2 - Get all rented books by student
    * 3 - Set all current rented by student to available
    * 4 - With each book title, get the book by title
    *    4.1 - If the book is available, set it to unavailable and set student to book
    *            If not  throw exception
    * 5 - Set student books to new rented books
    * */
    @PostMapping("/rentbooks/{studentId}")
    public ResponseEntity<List<BookEntity>> rentBooks(@PathVariable UUID studentId, @RequestBody List<String> booksTitlesList) {
        Optional<StudentEntity> studentRent = studentService.findById(studentId);
        if (!studentRent.isPresent()) {
            return new ResponseEntity("Student not found", HttpStatus.BAD_REQUEST);
        }
        List<BookEntity> currentRentedBooks = bookService.findByStudentId(studentId);
        List<BookEntity> newBookToRent = new ArrayList();
        currentRentedBooks.forEach(book -> {
            book.setStudent(null);
            book.setAvailable(true);
            bookService.save(book);
        });
        for (String book : booksTitlesList) {
            Optional<BookEntity> foundBook = bookService.findByTile(book);
            if (foundBook.isPresent()) {
                if (foundBook.get().getAvailable()) {
                    foundBook.get().setAvailable(false);
                    foundBook.get().setStudent(studentService.findById(studentId).get());
                    newBookToRent.add(foundBook.get());
                    bookService.save(foundBook.get());
                } else {
                    return new ResponseEntity("Book tile " + book +" not available", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity("Book tile " + book+ " not found", HttpStatus.BAD_REQUEST);
            }
        }
        studentRent.get().setBooks(newBookToRent);
        studentService.save(studentRent.get());
        return ResponseEntity.ok(newBookToRent);
    }
}
