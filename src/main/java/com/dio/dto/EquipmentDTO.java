package com.dio.dto;

import java.util.List;

public record EquipmentDTO(
        String name,
        String brand,
        String description,
        String lifespan,
        double quantity,
        double priceUnit,
        List<String> id_inputs_hospitalSupplies
) {}
