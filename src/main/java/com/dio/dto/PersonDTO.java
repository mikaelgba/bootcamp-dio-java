package com.dio.dto;

public record PersonDTO(
        String name,
        int age,
        String rolePerson,
        double salary,
        boolean active
) {}