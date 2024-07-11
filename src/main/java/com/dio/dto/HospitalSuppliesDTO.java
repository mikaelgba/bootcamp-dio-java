package com.dio.dto;

public record HospitalSuppliesDTO(
        String name,
        String description,
        String expirationDate,
        double quantity,
        double priceUnit
) {}
