package com.example.tp1;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "title")
    private String title;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "student_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;
    public Book(String code, String title) {
        this.code = code;
        this.title = title;
    }
    public Book() {
    }
}
