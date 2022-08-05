package ru.baykov.springcourserest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.baykov.springcourserest.dto.PersonDTO;
import ru.baykov.springcourserest.model.Person;
import ru.baykov.springcourserest.service.PersonService;
import ru.baykov.springcourserest.util.PersonErrorResponse;
import ru.baykov.springcourserest.util.PersonNotCreatedException;
import ru.baykov.springcourserest.util.PersonNotFoundException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return personService.getPeople().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        return convertToPersonDTO(personService.getPerson(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult errors) {
        if (errors.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            errors.getFieldErrors().forEach(error ->
                    errorMsg.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append(";")
            );
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        Person person = convertToPerson(personDTO);
        personService.savePerson(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException ex) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id was not found.",
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException ex) {
        PersonErrorResponse response = new PersonErrorResponse(
                ex.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
