package org.baykov.project1.util;

import org.baykov.project1.dao.PersonDAO;
import org.baykov.project1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> reader = personDAO.getPersonByFcs(person.getFcs());
        if (reader.isPresent()) {
            errors.rejectValue("fcs", "", "This reader already exists.");
        }
    }
}
