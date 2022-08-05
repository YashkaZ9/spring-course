package org.baykov.project1.controller;

import org.baykov.project1.dao.BookDAO;
import org.baykov.project1.dao.PersonDAO;
import org.baykov.project1.model.Book;
import org.baykov.project1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String allBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/showBooks";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping("/new")
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult errors) {
        if (errors.hasErrors()) {
            return "books/newBook";
        }
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showBook(@PathVariable int id, Model model) {
        model.addAttribute(bookDAO.getBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}/edit")
    public String editBook(@PathVariable int id, @ModelAttribute("book") @Valid Book book,
                           BindingResult errors) {
        if (errors.hasErrors()) {
            return "books/editBook";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        Optional<Person> reader = bookDAO.getReader(id);
        if (reader.isPresent()) {
            model.addAttribute("reader", reader.get());
        } else {
            model.addAttribute("people", personDAO.getPeople());
        }
        return "books/showBook";
    }

    @PatchMapping("/{id}/assign")
    public String assignReader(@PathVariable int id, @ModelAttribute("person") Person person) {
        bookDAO.assignToPerson(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable int id) {
        bookDAO.releaseBook(id);
        return "redirect:/books/" + id;
    }
}
