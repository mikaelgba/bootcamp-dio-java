package com.dio.services;

import com.dio.dto.HospitalClinicDTO;
import com.dio.models.HospitalClinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalClinicService {

    Page<HospitalClinic> findAll(Pageable pageable);

    HospitalClinic findById(String id);

    HospitalClinic save(HospitalClinicDTO hcDTO);

    HospitalClinic update(String id, HospitalClinicDTO hcDTO);

    void delete(String id);

}
