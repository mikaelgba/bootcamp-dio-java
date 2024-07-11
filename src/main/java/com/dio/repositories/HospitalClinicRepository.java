package com.dio.repositories;

import com.dio.models.HospitalClinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalClinicRepository extends JpaRepository<HospitalClinic, String> {

    Page<HospitalClinic> findByActiveTrue(Pageable pageable);

    @Query("SELECT h FROM hospitalclinics h WHERE h.name = :nameParam")
    HospitalClinic findByHSName(@Param("nameParam") String name);
}
