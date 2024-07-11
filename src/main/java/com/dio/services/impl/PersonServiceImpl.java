package com.dio.services.impl;

import com.dio.dto.PersonDTO;
import com.dio.exceptions.exceptions.AlreadyExistsException;
import com.dio.exceptions.exceptions.InvalidDataException;
import com.dio.exceptions.exceptions.NotFoundException;
import com.dio.models.Person;
import com.dio.repositories.PersonRepository;
import com.dio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findByActiveTrue(pageable);
    }

    @Override
    public Person findById(String id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found"));
    }

    @Override
    public Person save(PersonDTO personDTO) {

        Person existingPerson = personRepository.findByNamePerson(personDTO.name());

        if (existingPerson != null) throw new AlreadyExistsException("Person already exists");

        validateDTO(personDTO);

        Person person = new Person();
        person.setName(personDTO.name());
        person.setAge(personDTO.age());
        person.setRolePerson(personDTO.rolePerson());
        person.setSalary(personDTO.salary());
        person.setActive(true);
        person.setCreated(LocalDateTime.now());
        person.setUpdated(LocalDateTime.now());
        return personRepository.save(person);
    }

    @Override
    public Person update(String id, PersonDTO personDTO) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found"));

        validateDTO(personDTO);

        person.setName(personDTO.name());
        person.setAge(personDTO.age());
        person.setRolePerson(personDTO.rolePerson());
        person.setSalary(personDTO.salary());
        person.setUpdated(LocalDateTime.now());
        return personRepository.save(person);
    }

    @Override
    public void delete(String id) {
        Optional<Person> optionalEquipment = personRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Person person = optionalEquipment.get();
            person.setActive(false);
            personRepository.save(person);
        }
    }

    private void validateDTO(PersonDTO personDTO) {
        if (Stream.of(personDTO.name()).anyMatch(Objects::isNull))
            throw new InvalidDataException("Name cannot be null");
    }
}
