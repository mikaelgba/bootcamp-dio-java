package com.dio.controllers;

import com.dio.dto.EquipmentDTO;
import com.dio.models.Equipment;
import com.dio.services.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipments")
@Tag(name = "Equipment", description = "the Equipment Api")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;


    @Operation(summary = "Get page equipments, default 10 per page")
    @GetMapping
    public ResponseEntity<Page<Equipment>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(equipmentService.findAll(pageable));
    }

    @Operation(summary = "Get equipment by id")
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable String id) {
        Equipment equipment = equipmentService.findById(id);
        return equipment != null ? ResponseEntity.ok(equipment) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create new equipment")
    @PostMapping
    public ResponseEntity<Equipment> create(@RequestBody @Validated EquipmentDTO equipmentDTO) {
        Equipment savedEquipment = equipmentService.save(equipmentDTO);
        return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);
    }

    @Operation(summary = "Update new equipment by id with equipmentDTO")
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable String id, @RequestBody @Validated EquipmentDTO equipmentDTO) {
        Equipment updatedEquipment = equipmentService.update(id, equipmentDTO);
        return ResponseEntity.ok(updatedEquipment);
    }

    @Operation(summary = "Delete equipment by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
