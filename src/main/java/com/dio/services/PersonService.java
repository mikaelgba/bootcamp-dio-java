package com.dio.services;

import com.dio.dto.PersonDTO;
import com.dio.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

    Page<Person> findAll(Pageable pageable);

    Person findById(String id);

    Person save(PersonDTO personDTO);

    Person update(String id, PersonDTO personDTO);

    void delete(String id);
}
