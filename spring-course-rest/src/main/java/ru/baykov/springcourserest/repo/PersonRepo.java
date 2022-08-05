package ru.baykov.springcourserest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baykov.springcourserest.model.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
}
