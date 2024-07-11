package com.dio.repositories;

import com.dio.models.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {

    Page<Equipment> findByActiveTrue(Pageable pageable);

    @Query("SELECT e FROM equipments e WHERE e.name = :nameParam AND e.brand = :brandParam")
    Equipment findByEquipmentNameAndBrand(@Param("nameParam") String name, @Param("brandParam") String brand);
}
