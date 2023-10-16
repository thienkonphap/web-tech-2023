package com.example.tp1.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Student(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Integer age) {
}