package com.example.tp1.controllers;

import com.example.tp1.entity.BookEntity;
import com.example.tp1.entity.StudentEntity;
import com.example.tp1.services.BookService;
import com.example.tp1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
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

    // Get all books current rented by a student_id

    @GetMapping("/students/{studentId}")
    public List<BookEntity> findByStudentId(@PathVariable UUID studentId) {
        return bookService.findByStudentId(studentId);
    }

    @PostMapping("/rentbooks/{studentId}")
    public List<BookEntity> rentBooks(@PathVariable UUID studentId, @RequestBody List<String> booksTitlesList) {
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
        Optional<StudentEntity> studentRent = studentService.findById(studentId);
        if(!studentRent.isPresent()) {
            throw new RuntimeException("Student not found");
        }
        List<BookEntity> currentRentedBooks = bookService.findByStudentId(studentId);
        List<BookEntity> newBookToRent = new ArrayList();
        currentRentedBooks.forEach(book -> {
            book.setStudent(null);
            book.setAvailable(true);
            bookService.save(book);
        });
        booksTitlesList.forEach(
                book -> {
                    Optional<BookEntity> foundBook = bookService.findByTile(book);
                    if (foundBook.isPresent()) {
                        if(foundBook.get().getAvailable()) {
                            foundBook.get().setAvailable(false);
                            foundBook.get().setStudent(studentService.findById(studentId).get());
                            newBookToRent.add(foundBook.get());
                            bookService.save(foundBook.get());
                        } else {
                            throw new RuntimeException("Book is not available");
                        }
                    }

                }
        );
        studentRent.get().setBooks(newBookToRent);
        studentService.save(studentRent.get());
        return newBookToRent;
    }


}
