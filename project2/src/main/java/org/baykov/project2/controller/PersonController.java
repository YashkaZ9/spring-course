package org.baykov.project2.controller;

import org.baykov.project2.model.Person;
import org.baykov.project2.service.PersonService;
import org.baykov.project2.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String showPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "people/showPeople";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/newPerson";
    }

    @PostMapping("/new")
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult errors) {
        personValidator.validate(person, errors);
        if (errors.hasErrors()) {
            return "people/newPerson";
        }
        personService.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showPerson(@PathVariable int id, Model model) {
        Optional<Person> person = personService.getPerson(id);
        person.ifPresent(value -> model.addAttribute("person", value));
        return "people/editPerson";
    }

    @PatchMapping("/{id}/edit")
    public String editPerson(@PathVariable int id, @ModelAttribute("person") @Valid Person person,
                             BindingResult errors) {
        personValidator.validate(person, errors);
        if (errors.hasErrors()) {
            return "people/editPerson";
        }
        personService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable int id, Model model) {
        Optional<Person> person = personService.getPerson(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("books", personService.getReaderBooks(id));
        }
        return "people/showPerson";
    }
}
