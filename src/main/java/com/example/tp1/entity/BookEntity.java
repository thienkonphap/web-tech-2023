package com.example.tp1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "books")
@Data
public class BookEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "title")
    private String title;
    @Column(name = "available")
    private Boolean available = true;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "student_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StudentEntity student;
}
