package com.dio.services;

import com.dio.dto.EquipmentDTO;
import com.dio.models.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentService {

    Page<Equipment> findAll(Pageable pageable);

    Equipment findById(String id);

    Equipment save(EquipmentDTO equipmentDTO);

    Equipment update(String id, EquipmentDTO equipmentDTO);

    void delete(String id);

}
