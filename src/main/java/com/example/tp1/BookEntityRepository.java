package com.example.tp1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEntityRepository extends JpaRepository<Book,Integer> {
    public List<Book> findAll();
    public Book save(Book book);
}
