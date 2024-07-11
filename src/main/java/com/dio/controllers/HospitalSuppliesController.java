package com.dio.controllers;

import com.dio.dto.HospitalSuppliesDTO;
import com.dio.models.HospitalSupplies;
import com.dio.services.HospitalSuppliesService;
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
@RequestMapping("/hospitalsupplies")
@Tag(name = "Hospital supplies", description = "The Hospital supplies Api")
public class HospitalSuppliesController {

    @Autowired
    private HospitalSuppliesService hospitalSuppliesService;


    @Operation(summary = "Get page Hospital supplies, default 10 per page")
    @GetMapping
    public ResponseEntity<Page<HospitalSupplies>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hospitalSuppliesService.findAll(pageable));
    }

    @Operation(summary = "Get Hospital supplies by id")
    @GetMapping("/{id}")
    public ResponseEntity<HospitalSupplies> findById(@PathVariable String id) {
        HospitalSupplies hs = hospitalSuppliesService.findById(id);
        return hs != null ? ResponseEntity.ok(hs) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create new Hospital supplies")
    @PostMapping
    public ResponseEntity<HospitalSupplies> create(@RequestBody @Validated HospitalSuppliesDTO hsDTO) {
        HospitalSupplies savedHS = hospitalSuppliesService.save(hsDTO);
        return new ResponseEntity<>(savedHS, HttpStatus.CREATED);
    }

    @Operation(summary = "Update new Hospital supplies by id with hsDTO")
    @PutMapping("/{id}")
    public ResponseEntity<HospitalSupplies> update(@PathVariable String id, @RequestBody @Validated HospitalSuppliesDTO hsDTO) {
        HospitalSupplies updatedHS = hospitalSuppliesService.update(id, hsDTO);
        return ResponseEntity.ok(updatedHS);
    }

    @Operation(summary = "Delete Hospital supplies by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hospitalSuppliesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
