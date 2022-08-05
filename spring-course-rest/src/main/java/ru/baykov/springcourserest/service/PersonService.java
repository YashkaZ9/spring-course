package ru.baykov.springcourserest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baykov.springcourserest.model.Person;
import ru.baykov.springcourserest.repo.PersonRepo;
import ru.baykov.springcourserest.util.PersonNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getPeople() {
        return personRepo.findAll();
    }

    public Person getPerson(int id) {
        Optional<Person> person = personRepo.findById(id);
        return person.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void savePerson(Person person) {
        enrichPerson(person);
        personRepo.save(person);
    }

    public void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }
}
