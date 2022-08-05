package org.baykov.springbasecourse.repo;

import org.baykov.springbasecourse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
    List<Person> findByEmail(String email);
    List<Person> findByNameOrderByAge(String name);
    List<Person> findByNameOrEmail(String name, String email);
    List<Person> findByAddressStartingWith(String address);
}
