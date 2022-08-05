package org.baykov.project2.service;

import org.baykov.project2.model.Book;
import org.baykov.project2.model.Person;
import org.baykov.project2.repo.PersonRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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

    public Optional<Person> getPerson(int id) {
        return personRepo.findById(id);
    }

    public Optional<Person> getPersonByFcs(String fcs) {
        return personRepo.findPersonByFcs(fcs);
    }

    @Transactional
    public void addPerson(Person person) {
        personRepo.save(person);
    }

    @Transactional
    public void updatePerson(int id, Person person) {
        person.setId(id);
        personRepo.save(person);
    }

    @Transactional
    public void deletePerson(int id) {
        personRepo.deleteById(id);
    }

    public List<Book> getReaderBooks(int id) {
        Optional<Person> reader = personRepo.findById(id);
        if (reader.isPresent()) {
            Hibernate.initialize(reader.get().getBooks());
            return reader.get().getBooks();
        }
        return Collections.emptyList();
    }
}
