package org.baykov.springbasecourse.controller;

import org.baykov.springbasecourse.dao.PersonDAO;
import org.baykov.springbasecourse.model.Mood;
import org.baykov.springbasecourse.model.Person;
import org.baykov.springbasecourse.service.ItemService;
import org.baykov.springbasecourse.service.PersonService;
import org.baykov.springbasecourse.util.PersonValidator;
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
    private final PersonService personService;
    private final ItemService itemService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonService personService,
                            ItemService itemService, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personService = personService;
        this.itemService = itemService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
//        itemService.findByTitle("Airpods");
//        itemService.findByOwner(personService.getPeople().get(0));
        personService.test();

//        personDAO.testNPlus1();

//        personDAO.testGetVsLoad();
        return "people/showPeople";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/showPerson";
    }

    @GetMapping("/new")
    public String addNewPerson(@ModelAttribute("person") Person person) {
        return "people/newPerson";
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/newPerson";
        }
        person.setMood(Mood.CALM);
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/editPerson";
        }
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
