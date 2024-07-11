package com.dio.dto;

import com.dio.models.Equipment;
import com.dio.models.HospitalSupplies;
import com.dio.models.Person;
import java.util.List;

public record HospitalClinicDTO(
        String name,
        String description,
        double capMax,
        List<Person> employees,
        List<HospitalSupplies> hospitalSupplies,
        List<Equipment> equipments
) {}
