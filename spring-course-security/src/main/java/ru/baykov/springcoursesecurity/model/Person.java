package ru.baykov.springcoursesecurity.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 100, message = "Username length should be between 2 and 100 symbols.")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Password should not be empty.")
    @Size(max = 100, message = "Entered password is too long.")
    @Column(name = "password")
    private String password;

    @Min(value = 1900, message = "Year should be more than 1900.")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "role")
    private String role;

    public Person() {
    }

    public Person(String username, String password, int yearOfBirth) {
        this.username = username;
        this.password = password;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getYearOfBirth() != person.getYearOfBirth()) return false;
        if (!getUsername().equals(person.getUsername())) return false;
        return getPassword().equals(person.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getYearOfBirth();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
