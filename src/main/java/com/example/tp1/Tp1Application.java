package com.example.tp1;

import com.example.tp1.repository.StudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp1Application {
    @Autowired
    private StudentEntityRepository student;

    public static void main(String[] args) {
        SpringApplication.run(Tp1Application.class, args);
        System.out.println("Hello World!");
    }
}
