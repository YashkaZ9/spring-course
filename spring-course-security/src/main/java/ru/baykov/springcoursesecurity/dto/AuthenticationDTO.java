package ru.baykov.springcoursesecurity.dto;

import javax.validation.constraints.Size;

public class AuthenticationDTO {
    @Size(min = 2, max = 100, message = "Username length should be between 2 and 100 symbols.")
    private String username;
    private String password;

    public AuthenticationDTO() {}

    public AuthenticationDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
}
