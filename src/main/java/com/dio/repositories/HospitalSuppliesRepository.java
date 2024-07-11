package com.dio.repositories;

import com.dio.models.HospitalSupplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalSuppliesRepository extends JpaRepository<HospitalSupplies, String> {

    Page<HospitalSupplies> findByActiveTrue(Pageable pageable);

    @Query("SELECT h FROM hospitalsupplies h WHERE lower(h.name) = lower(:nameParam)")
    HospitalSupplies findByNameHospitalsupplies(@Param("nameParam") String name);
}
