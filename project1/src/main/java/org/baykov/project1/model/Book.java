package org.baykov.project1.model;

import javax.validation.constraints.*;

public class Book {
    private int id;
    @NotEmpty(message = "The book must have title.")
    @Size(max = 100, message = "The book title is too long.")
    private String title;
    @Size(min = 3, max = 100, message = "Author name symbols count must be between 3 and 100.")
    private String author;
    @Min(value = 1500, message = "The year of book writing must be valid.")
    private int yearOfCreation;

    public Book() {}

    public Book(int id, String title, String author, int yearOfCreation) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfCreation = yearOfCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
