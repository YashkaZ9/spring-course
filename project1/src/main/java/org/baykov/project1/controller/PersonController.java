package org.baykov.project1.controller;

import org.baykov.project1.dao.PersonDAO;
import org.baykov.project1.model.Person;
import org.baykov.project1.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String showPeople(Model model) {
        model.addAttribute("people", personDAO.getPeople());
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
        personDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showPerson(@PathVariable int id, Model model) {
        model.addAttribute(personDAO.getPerson(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}/edit")
    public String editPerson(@PathVariable int id, @ModelAttribute("person") @Valid Person person,
                             BindingResult errors) {
        personValidator.validate(person, errors);
        if (errors.hasErrors()) {
            return "people/editPerson";
        }
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", personDAO.getReaderBooks(id));
        return "people/showPerson";
    }
}
