package com.example.tp1.controllers;

import com.example.tp1.entity.BookEntity;
import com.example.tp1.services.BookService;
import com.example.tp1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // Get all books rented by a student_id
    @GetMapping("/students/{studentId}")
    public List<BookEntity> findByStudentId(@PathVariable String studentId) {
        return bookService.findByStudentId(Integer.parseInt(studentId));
    }

    @PostMapping("/rentbooks/{studentId}")
    public List<BookEntity> rentBooks(@PathVariable String studentId, @RequestBody List<String> booksCode) {
        /*
         *  1. Get all current rented books by a student_id
         *     -> Set these books to available
         *  2. With each book in booksCode
         *   -> Verify if book is available
         *   -> If available
         *       -> Set book to unavailable
         *       -> Set student_id to booksCode
         *   -> If not available
         *    -> throw exception or send error message
         *  3. Return new rented books for student_id
         * */
        List<BookEntity> books = findByStudentId(studentId);
        books.forEach(book -> {
            System.out.println(book);
            book.setAvailable(true);
            book.setStudent(null);
            bookService.save(book);
        });
        booksCode.forEach(book -> {
            System.out.println(book);
            Optional<BookEntity> bookToRent = bookService.findByBookCode(book);
            bookToRent.ifPresent(value -> {
                if (value.getAvailable()) {
                    value.setAvailable(false);
                    value.setStudent(studentService.findById(studentId).orElse(null));
                    studentService.findById(studentId).ifPresent(student -> {
                        student.getBooks().add(value);
                        studentService.save(student);
                    });
                    bookService.save(value);
                }
            });
            bookService.save(bookToRent.orElse(null));
        });
        return bookService.findByStudentId(Integer.parseInt(studentId));
    }

}
