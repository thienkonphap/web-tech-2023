package com.example.tp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp1Application {
    @Autowired
    private StudentEntityRepository student;
    public static void main(String[] args) {
        SpringApplication.run(Tp1Application.class, args);
        System.out.println("Hello World!");
    }
    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {
            Student s1 = new Student("Joe", "Bide", "joe.biden@gmail.com");
            // student.save(s1);
            student.findAll().forEach(System.out::println);
        };
    }

}
