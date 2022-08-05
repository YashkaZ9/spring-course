package ru.baykov.springcourseboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baykov.springcourseboot.model.Book;
import ru.baykov.springcourseboot.model.Person;
import ru.baykov.springcourseboot.repo.BookRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public List<Book> sortedBooks() {
        return bookRepo.findAll(Sort.by("yearOfCreation"));
    }

    public List<Book> pagedBooks(int page, int booksPerPage) {
        return bookRepo.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> sortedPagedBooks(int page, int booksPerPage) {
        return bookRepo.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfCreation")))
                .getContent();
    }

    public Optional<Book> getBook(int id) {
        return bookRepo.findById(id);
    }

    @Transactional
    public void addBook(Book book) {
        bookRepo.save(book);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook) {
        Book bookToBeUpdated = bookRepo.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setReader(bookToBeUpdated.getReader());
        bookRepo.save(updatedBook);
    }

    @Transactional
    public void deleteBook(int id) {
        bookRepo.deleteById(id);
    }

    @Transactional
    public void assignToPerson(int id, Person person) {
        bookRepo.findById(id).ifPresent(
                book -> {
                    book.setReader(person);
                    book.setTakenAt(new Date());
                });
    }

    @Transactional
    public void releaseBook(int id) {
        bookRepo.findById(id).ifPresent(
                book -> {
                    book.setReader(null);
                    book.setTakenAt(null);
                });
    }

    public List<Book> getRequestedBooks(String title) {
        return bookRepo.findByTitleStartingWith(title);
    }
}
