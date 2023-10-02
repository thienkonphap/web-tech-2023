package com.example.tp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookEntityRepository bookEntityRepository;

    @GetMapping
    public Iterable<Book> findAll() {
        return bookEntityRepository.findAll();
    }
    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookEntityRepository.save(book);
    }
}
