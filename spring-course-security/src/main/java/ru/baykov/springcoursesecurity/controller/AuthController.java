package ru.baykov.springcoursesecurity.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.baykov.springcoursesecurity.dto.AuthenticationDTO;
import ru.baykov.springcoursesecurity.dto.PersonDTO;
import ru.baykov.springcoursesecurity.model.Person;
import ru.baykov.springcoursesecurity.security.JWTUtil;
import ru.baykov.springcoursesecurity.service.AdminService;
import ru.baykov.springcoursesecurity.service.RegistrationService;
import ru.baykov.springcoursesecurity.util.PersonValidator;

import javax.validation.Valid;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final AdminService adminService;
    private final PersonValidator personValidator;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(RegistrationService registrationService, AdminService adminService, PersonValidator personValidator, ModelMapper modelMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.adminService = adminService;
        this.personValidator = personValidator;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

//    @GetMapping("/login")
//    public String loginPage() {
//        return "/auth/login";
//    }

//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person")Person person) {
//        return "/auth/registration";
//    }

//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("person") @Valid Person person,
//                                      BindingResult errors) {
//        personValidator.validate(person, errors);
//        if (errors.hasErrors()) {
//            return "/auth/registration";
//        }
//        registrationService.register(person);
//        return "redirect:/auth/login";
//    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult errors) {
        personValidator.validate(personDTO, errors);
        if (errors.hasErrors()) {
            return Map.of("error", "Registration error.");
        }

        Person person = convertToPerson(personDTO);
        registrationService.register(person);
        String token = jwtUtil.generateToken(personDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getUsername(), authenticationDTO.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException ex) {
            return Map.of("error", "Incorrect credentials");
        }
        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "/auth/admin";
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
