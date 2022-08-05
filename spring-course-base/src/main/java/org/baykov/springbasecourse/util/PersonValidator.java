package org.baykov.springbasecourse.util;

import org.baykov.springbasecourse.model.Person;
import org.baykov.springbasecourse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        if (emailIsWrong(person)) {
            errors.rejectValue("email", "", "Этот email уже существует.");
        }
    }

    private boolean emailIsWrong(Person updatedPerson) {
        Person foundPerson = personService.getPersonByEmail(updatedPerson.getEmail())
                .stream().findAny().orElse(null);
        return foundPerson != null && foundPerson.getId() != updatedPerson.getId();
    }
}
