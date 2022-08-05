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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year_of_creation) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfCreation());
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year_of_creation = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getYearOfCreation(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    public void assignToPerson(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id = ?", person.getId(), id);
    }

    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE id = ?", id);
    }

    public Optional<Person> getReader(int id) {
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id = person.id WHERE book.id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}