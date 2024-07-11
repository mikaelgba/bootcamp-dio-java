package com.dio.services.impl;

import com.dio.dto.HospitalClinicDTO;
import com.dio.exceptions.exceptions.AlreadyExistsException;
import com.dio.exceptions.exceptions.InvalidDataException;
import com.dio.exceptions.exceptions.NotFoundException;
import com.dio.models.HospitalClinic;
import com.dio.repositories.HospitalClinicRepository;
import com.dio.services.HospitalClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class HospitalClinicServiceImpl implements HospitalClinicService {

    @Autowired
    private HospitalClinicRepository hospitalClinicRepository;

    @Override
    public Page<HospitalClinic> findAll(Pageable pageable) {
        return hospitalClinicRepository.findByActiveTrue(pageable);
    }

    @Override
    public HospitalClinic findById(String id) {
        return hospitalClinicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital Clinic not found"));
    }

    @Override
    public HospitalClinic save(HospitalClinicDTO hcDTO) {

        HospitalClinic existingHC = hospitalClinicRepository.findByHSName(hcDTO.name());

        if (existingHC != null) throw new AlreadyExistsException("Hospital Clinic already exists");

        validateDTO(hcDTO);

        HospitalClinic hc = new HospitalClinic();
        hc.setName(hcDTO.name());
        hc.setDescription(hcDTO.description());
        hc.setCapMax(hcDTO.capMax());
        hc.setEmployees(hcDTO.employees());
        hc.setHospitalSupplies(hcDTO.hospitalSupplies());
        hc.setEquipments(hcDTO.equipments());
        hc.setActive(true);
        hc.setCreated(LocalDateTime.now());
        hc.setUpdated(LocalDateTime.now());
        return hospitalClinicRepository.save(hc);
    }

    @Override
    public HospitalClinic update(String id, HospitalClinicDTO hcDTO) {

        HospitalClinic hc = hospitalClinicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital Clinic not found"));

        validateDTO(hcDTO);
        hc.setName(hcDTO.name());
        hc.setDescription(hcDTO.description());
        hc.setCapMax(hcDTO.capMax());
        hc.setEmployees(hcDTO.employees());
        hc.setHospitalSupplies(hcDTO.hospitalSupplies());
        hc.setEquipments(hcDTO.equipments());
        hc.setActive(true);
        hc.setCreated(LocalDateTime.now());
        hc.setUpdated(LocalDateTime.now());
        return hospitalClinicRepository.save(hc);
    }

    @Override
    public void delete(String id) {
        Optional<HospitalClinic> optionalEquipment = hospitalClinicRepository.findById(id);

        if (optionalEquipment.isPresent()) {
            HospitalClinic hc = optionalEquipment.get();
            hc.setActive(false);
            hospitalClinicRepository.save(hc);
        }
    }

    private void validateDTO(HospitalClinicDTO hcDTO) {
        if (Stream.of(hcDTO.name()).anyMatch(Objects::isNull))
            throw new InvalidDataException("Name cannot be null");
    }
}
