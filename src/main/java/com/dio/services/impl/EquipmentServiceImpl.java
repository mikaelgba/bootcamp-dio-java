package com.dio.services.impl;

import com.dio.dto.EquipmentDTO;
import com.dio.exceptions.exceptions.AlreadyExistsException;
import com.dio.exceptions.exceptions.InvalidDataException;
import com.dio.exceptions.exceptions.NotFoundException;
import com.dio.models.Equipment;
import com.dio.repositories.EquipmentRepository;
import com.dio.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Page<Equipment> findAll(Pageable pageable) {
        return equipmentRepository.findByActiveTrue(pageable);
    }

    @Override
    public Equipment findById(String id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipment not found"));
    }

    @Override
    public Equipment save(EquipmentDTO equipmentDTO) {

        Equipment existingEquipment = equipmentRepository.findByEquipmentNameAndBrand(
                equipmentDTO.name(),
                equipmentDTO.brand()
        );

        if (existingEquipment != null) throw new AlreadyExistsException("Equipment already exists");

        validateDTO(equipmentDTO);
        Equipment equipment = new Equipment();
        equipment.setName(equipmentDTO.name());
        equipment.setBrand(equipmentDTO.brand());
        equipment.setDescription(equipmentDTO.description());
        equipment.setLifespan(equipmentDTO.lifespan());
        equipment.setQuantity(equipmentDTO.quantity());
        equipment.setPriceUnit(equipmentDTO.priceUnit());
        equipment.setInputs_hospitalSupplies(equipmentDTO.id_inputs_hospitalSupplies());
        equipment.setActive(true);
        equipment.setCreated(LocalDateTime.now());
        equipment.setUpdated(LocalDateTime.now());
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment update(String id, EquipmentDTO equipmentDTO) {

        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipment not found"));

        validateDTO(equipmentDTO);

        equipment.setName(equipmentDTO.name());
        equipment.setBrand(equipmentDTO.brand());
        equipment.setDescription(equipmentDTO.description());
        equipment.setLifespan(equipmentDTO.lifespan());
        equipment.setQuantity(equipmentDTO.quantity());
        equipment.setPriceUnit(equipmentDTO.priceUnit());
        equipment.setInputs_hospitalSupplies(equipmentDTO.id_inputs_hospitalSupplies());
        equipment.setUpdated(LocalDateTime.now());
        return equipmentRepository.save(equipment);

    }

    @Override
    public void delete(String id) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            equipment.setActive(false);
            equipmentRepository.save(equipment);
        }
    }

    private void validateDTO(EquipmentDTO equipmentDTO) {
        if (Stream.of(equipmentDTO.name(), equipmentDTO.brand()).anyMatch(Objects::isNull))
            throw new InvalidDataException("Name and Brand cannot be null");
    }
}
