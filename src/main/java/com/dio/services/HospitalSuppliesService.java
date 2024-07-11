package com.dio.services;

import com.dio.dto.HospitalSuppliesDTO;
import com.dio.models.HospitalSupplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalSuppliesService {

    Page<HospitalSupplies> findAll(Pageable pageable);

    HospitalSupplies findById(String id);

    HospitalSupplies save(HospitalSuppliesDTO personDTO);

    HospitalSupplies update(String id, HospitalSuppliesDTO personDTO);

    void delete(String id);
}
