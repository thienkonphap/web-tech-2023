package com.example.tp1.domain;

import com.example.tp1.entity.StudentEntity;
import lombok.Builder;

@Builder
public record Book(
        Integer id,
        String code,
        String title,
        Boolean available,
        StudentEntity student) {

}
