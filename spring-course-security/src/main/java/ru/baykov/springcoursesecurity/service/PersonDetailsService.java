package ru.baykov.springcoursesecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.baykov.springcoursesecurity.model.Person;
import ru.baykov.springcoursesecurity.repo.PersonRepo;
import ru.baykov.springcoursesecurity.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonDetailsService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = personRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new PersonDetails(user.get());
    }
}
