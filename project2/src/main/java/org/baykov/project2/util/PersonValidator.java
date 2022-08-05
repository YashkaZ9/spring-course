package org.baykov.project2.util;

import org.baykov.project2.model.Person;
import org.baykov.project2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> reader = personService.getPersonByFcs(person.getFcs());
        if (reader.isPresent()) {
            errors.rejectValue("fcs", "", "This reader already exists.");
        }
    }
}
