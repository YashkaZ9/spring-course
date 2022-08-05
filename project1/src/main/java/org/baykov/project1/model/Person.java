package org.baykov.project1.model;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @Size(min = 5, max = 100, message = "Your fcs symbols count must be between 5 and 100.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я-]* [A-ZА-Я][a-zа-я-]* [A-ZА-Я][a-zа-я-]*",
            message = "The format of fcs must be: Surname Name Patronymic.")
    private String fcs;
    @Min(value = 1900, message = "The year of birth must be valid.")
    private int yearOfBirth;

    public Person() {}

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
}
