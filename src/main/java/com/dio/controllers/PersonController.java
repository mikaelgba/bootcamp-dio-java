package com.dio.controllers;

import com.dio.dto.PersonDTO;
import com.dio.models.Person;
import com.dio.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@Tag(name = "Person", description = "the Person Api")
public class PersonController {

    @Autowired
    private PersonService personService;


    @Operation(summary = "Get page persons, default 10 per page")
    @GetMapping
    public ResponseEntity<Page<Person>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.findAll(pageable));
    }

    @Operation(summary = "Get person by id")
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable String id) {
        Person person = personService.findById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create new person")
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody @Validated PersonDTO personDTO) {
        Person savedPersonDTO = personService.save(personDTO);
        return new ResponseEntity<>(savedPersonDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update new person by id with personDTO")
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable String id, @RequestBody @Validated PersonDTO personDTO) {
        Person updatedPerson = personService.update(id, personDTO);
        return ResponseEntity.ok(updatedPerson);
    }

    @Operation(summary = "Delete person by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
