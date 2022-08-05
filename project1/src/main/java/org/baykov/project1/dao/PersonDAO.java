package org.baykov.project1.dao;

import org.baykov.project1.model.Book;
import org.baykov.project1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonByFcs(String fcs) {
        return jdbcTemplate.query("SELECT * FROM person WHERE fcs = ?", new Object[]{fcs},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(fcs, year_of_birth) VALUES (?, ?)",
                person.getFcs(), person.getYearOfBirth());
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET fcs = ?, year_of_birth = ? WHERE id = ?",
                person.getFcs(), person.getYearOfBirth(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public List<Book> getReaderBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
