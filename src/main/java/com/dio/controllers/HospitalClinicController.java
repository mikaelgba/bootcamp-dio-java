package com.dio.controllers;

import com.dio.dto.HospitalClinicDTO;
import com.dio.models.HospitalClinic;
import com.dio.services.HospitalClinicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitalclinics")
@Tag(name = "Hospital Clinic", description = "the Hospital Clinic Api")
public class HospitalClinicController {

    @Autowired
    private HospitalClinicService hospitalClinicService;

    @Operation(summary = "Get page hospital clinic, default 10 per page")
    @GetMapping
    public ResponseEntity<Page<HospitalClinic>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hospitalClinicService.findAll(pageable));
    }

    @Operation(summary = "Get hospital clinic by id")
    @GetMapping("/{id}")
    public ResponseEntity<HospitalClinic> findById(@PathVariable String id) {
        HospitalClinic hc = hospitalClinicService.findById(id);
        return hc != null ? ResponseEntity.ok(hc) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create new hospital clinic")
    @PostMapping
    public ResponseEntity<HospitalClinic> create(@RequestBody @Validated HospitalClinicDTO hcDTO) {
        HospitalClinic savedHC = hospitalClinicService.save(hcDTO);
        return new ResponseEntity<>(savedHC, HttpStatus.CREATED);
    }

    @Operation(summary = "Update new hospital clinic by id with hcDTO")
    @PutMapping("/{id}")
    public ResponseEntity<HospitalClinic> update(@PathVariable String id, @RequestBody @Validated HospitalClinicDTO hcDTO) {
        HospitalClinic updatedHC = hospitalClinicService.update(id, hcDTO);
        return ResponseEntity.ok(updatedHC);
    }

    @Operation(summary = "Delete hospital clinic by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hospitalClinicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
