package ru.baykov.springcourseboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.baykov.springcourseboot.model.Book;
import ru.baykov.springcourseboot.model.Person;
import ru.baykov.springcourseboot.service.BookService;
import ru.baykov.springcourseboot.service.PersonService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String getBooks(@RequestParam(name = "page", required = false) Integer page,
                             @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                             @RequestParam(name = "sort_by_year", required = false) boolean sortByYear,
                             Model model) {
        if (page != null && booksPerPage != null && sortByYear) {
            model.addAttribute("books", bookService.sortedPagedBooks(page, booksPerPage));
        } else if (page != null & booksPerPage != null) {
            model.addAttribute("books", bookService.pagedBooks(page, booksPerPage));
        } else if (sortByYear) {
            model.addAttribute("books", bookService.sortedBooks());
        } else {
            model.addAttribute("books", bookService.getBooks());
        }
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
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showBook(@PathVariable int id, Model model) {
        Optional<Book> book = bookService.getBook(id);
        book.ifPresent(value -> model.addAttribute("book", value));
        return "books/editBook";
    }

    @PatchMapping("/{id}/edit")
    public String editBook(@PathVariable int id, @ModelAttribute("book") @Valid Book book,
                           BindingResult errors) {
        if (errors.hasErrors()) {
            return "books/editBook";
        }
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, @ModelAttribute("person") Person person, Model model) {
        Optional<Book> book = bookService.getBook(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            Person reader = book.get().getReader();
            if (reader != null) {
                model.addAttribute("reader", reader);
            } else {
                model.addAttribute("people", personService.getPeople());
            }
        }
        return "books/showBook";
    }

    @PatchMapping("/{id}/assign")
    public String assignReader(@PathVariable int id, @ModelAttribute("person") Person person) {
        bookService.assignToPerson(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable int id) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "books/searchBooks";
    }

    @PostMapping("/search")
    public String findBooks(@RequestParam(name = "query") String title, Model model) {
        model.addAttribute("books", bookService.getRequestedBooks(title));
        return "books/searchBooks";
    }
}
