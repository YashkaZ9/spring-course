package ru.baykov.springcoursesecurity.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Username should not be empty.")
    @Size(min = 2, max = 100, message = "Username length should be between 2 and 100 symbols.")
    private String username;

    @NotEmpty(message = "Password should not be empty.")
    @Size(max = 100, message = "Entered password is too long.")
    private String password;

    @Min(value = 1900, message = "Year should be more than 1900.")
    private int yearOfBirth;

    public PersonDTO() {}

    public PersonDTO(String username, String password, int yearOfBirth) {
        this.username = username;
        this.password = password;
        this.yearOfBirth = yearOfBirth;
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
}
