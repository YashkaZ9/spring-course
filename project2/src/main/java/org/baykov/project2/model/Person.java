package org.baykov.project2.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fcs")
    @Size(min = 5, max = 100, message = "Your fcs symbols count must be between 5 and 100.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я-]* [A-ZА-Я][a-zа-я-]* [A-ZА-Я][a-zа-я-]*",
            message = "The format of fcs must be: Surname Name Patronymic.")
    private String fcs;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "The year of birth must be valid.")
    private int yearOfBirth;

    @OneToMany(mappedBy = "reader")
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String fcs, int yearOfBirth) {
        this.id = id;
        this.fcs = fcs;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFcs() {
        return fcs;
    }

    public void setFcs(String fcs) {
        this.fcs = fcs;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getYearOfBirth() != person.getYearOfBirth()) return false;
        return getFcs().equals(person.getFcs());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFcs().hashCode();
        result = 31 * result + getYearOfBirth();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fcs='" + fcs + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
