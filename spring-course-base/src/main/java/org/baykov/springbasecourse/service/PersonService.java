package org.baykov.springbasecourse.service;

import org.baykov.springbasecourse.model.Person;
import org.baykov.springbasecourse.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person getPerson(int id) {
        return personRepo.findById(id).orElse(null);
    }

    public List<Person> getPersonByEmail(String email) {
        return personRepo.findByEmail(email);
    }

    public List<Person> getPeople() {
        return personRepo.findAll();
    }

    @Transactional
    public void save(Person person) {
        person.setCreatedAt(new Date());
        personRepo.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepo.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepo.deleteById(id);
    }

    public void test() {
        System.out.println("Testing inside the hibernate transaction with debug.");
    }
}
