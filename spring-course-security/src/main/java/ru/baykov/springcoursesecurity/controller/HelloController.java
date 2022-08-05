package ru.baykov.springcoursesecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.baykov.springcoursesecurity.security.PersonDetails;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user-info")
    public String getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails user = (PersonDetails) authentication.getPrincipal();
        System.out.println(user.getPerson());
        return "hello";
    }
}
