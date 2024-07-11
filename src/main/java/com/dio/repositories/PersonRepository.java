package com.dio.repositories;

import com.dio.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Page<Person> findByActiveTrue(Pageable pageable);

    @Query("SELECT p FROM persons p WHERE lower(p.name) = lower(:nameParam)")
    Person findByNamePerson(@Param("nameParam") String name);
}
