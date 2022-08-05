package ru.baykov.springcourseboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.baykov.springcourseboot.model.Person;
import ru.baykov.springcourseboot.service.PersonService;

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
        if (reader.isPresent() && reader.get().getId() != person.getId()) {
            errors.rejectValue("fcs", "", "This reader already exists.");
        }
    }
}
