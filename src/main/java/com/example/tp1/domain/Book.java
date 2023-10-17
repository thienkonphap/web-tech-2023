package com.example.tp1.domain;

import com.example.tp1.entity.StudentEntity;
import lombok.Builder;

import java.util.UUID;

@Builder
public record Book(
        UUID id,
        String code,
        String title,
        Boolean available,
        StudentEntity student) {

}
