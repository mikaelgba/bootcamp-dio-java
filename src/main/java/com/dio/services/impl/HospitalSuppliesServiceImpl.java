package com.dio.services.impl;

import com.dio.dto.HospitalSuppliesDTO;
import com.dio.exceptions.exceptions.AlreadyExistsException;
import com.dio.exceptions.exceptions.InvalidDataException;
import com.dio.exceptions.exceptions.NotFoundException;
import com.dio.models.HospitalSupplies;
import com.dio.repositories.HospitalSuppliesRepository;
import com.dio.services.HospitalSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class HospitalSuppliesServiceImpl implements HospitalSuppliesService {

    @Autowired
    private HospitalSuppliesRepository hospitalSuppliesRepository;

    @Override
    public Page<HospitalSupplies> findAll(Pageable pageable) {
        return hospitalSuppliesRepository.findByActiveTrue(pageable);
    }

    @Override
    public HospitalSupplies findById(String id) {
        return hospitalSuppliesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital Supplies not found"));
    }

    @Override
    public HospitalSupplies save(HospitalSuppliesDTO hsDTO) {

        HospitalSupplies existingPerson = hospitalSuppliesRepository.findByNameHospitalsupplies(hsDTO.name());

        if (existingPerson != null) throw new AlreadyExistsException("Hospital Supplies already exists");

        validateDTO(hsDTO);

        HospitalSupplies hs = new HospitalSupplies();
        hs.setName(hsDTO.name());
        hs.setDescription(hsDTO.description());
        hs.setExpirationDate(hsDTO.expirationDate());
        hs.setQuantity(hsDTO.quantity());
        hs.setPriceUnit(hsDTO.priceUnit());
        hs.setActive(true);
        hs.setCreated(LocalDateTime.now());
        hs.setUpdated(LocalDateTime.now());
        return hospitalSuppliesRepository.save(hs);
    }

    @Override
    public HospitalSupplies update(String id, HospitalSuppliesDTO hsDTO) {

        HospitalSupplies hs = hospitalSuppliesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital Supplies not found"));;

        validateDTO(hsDTO);

        hs.setName(hsDTO.name());
        hs.setDescription(hsDTO.description());
        hs.setExpirationDate(hsDTO.expirationDate());
        hs.setQuantity(hsDTO.quantity());
        hs.setPriceUnit(hsDTO.priceUnit());
        hs.setActive(true);
        hs.setCreated(LocalDateTime.now());
        hs.setUpdated(LocalDateTime.now());
        return hospitalSuppliesRepository.save(hs);

    }

    @Override
    public void delete(String id) {

        Optional<HospitalSupplies> optionalEquipment = hospitalSuppliesRepository.findById(id);

        if (optionalEquipment.isPresent()) {
            HospitalSupplies hospitalSupplies = optionalEquipment.get();
            hospitalSupplies.setActive(false);
            hospitalSuppliesRepository.save(hospitalSupplies);
        }
    }

    private void validateDTO(HospitalSuppliesDTO hsDTO) {
        if (Stream.of(hsDTO.name()).anyMatch(Objects::isNull))
            throw new InvalidDataException("Name cannot be null");
    }
}
