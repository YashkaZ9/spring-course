package ru.baykov.springcoursesecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baykov.springcoursesecurity.model.Person;
import ru.baykov.springcoursesecurity.repo.PersonRepo;

@Service
public class RegistrationService {
    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepo personRepo, PasswordEncoder passwordEncoder) {
        this.personRepo = personRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepo.save(person);
    }
}
