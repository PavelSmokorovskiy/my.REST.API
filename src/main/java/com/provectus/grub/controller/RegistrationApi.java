package com.provectus.grub.controller;

import com.provectus.grub.model.ErrorDescription;
import com.provectus.grub.model.Role;
import com.provectus.grub.model.User;
import com.provectus.grub.persistence.UserRepository;
import com.provectus.grub.service.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationApi {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailNotification emailNotification;

    @Autowired
    private EmailActivation emailActivation;

    @RequestMapping(value = "/api/reg", method = RequestMethod.POST)
    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registration(@RequestParam String username, String password, String firstName, String lastName, String companyName) {

        //$ curl 'localhost:8080/api/reg?username=mail@email.com&password=String&firstName=String&lastName=String&companyName=String'

        try {

            if (repository.findByUsername(username).getUsername().equals(username)) {

                return new ResponseEntity<>(new ErrorDescription("User already exist"), HttpStatus.NOT_ACCEPTABLE);

            }

        } catch (Exception e) {

            if (emailNotification.isValidEmailAddress(username)) {

                if (password != null) {

                    User user = User.builder()
                            .id(repository.countAllBy() + 1)
                            .username(username)
                            .password(password)
                            .firstName(firstName)
                            .lastName(lastName)
                            .companyId(repository.countAllBy() + 1)
                            .companyName(companyName)
                            .roles(Role.USER)
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .enabled(true)
                            .emailConfirmation(false)
                            .activationCode(emailNotification.generateCode(10))
                            .build();

                    repository.save(user);

                } else {

                    return new ResponseEntity<>(new ErrorDescription("Password is not specified"), HttpStatus.NOT_ACCEPTABLE);
                }

            } else {

                return new ResponseEntity<>(new ErrorDescription("email is incorrect"), HttpStatus.NOT_ACCEPTABLE);
            }
        }

        return emailActivation.sendActivationCode(username);
    }

    @RequestMapping(value = "/api/adddata", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity addUsersData(@RequestParam String username, String firstName, String lastName, String companyName) {

        //$ curl 'localhost:8080/api/adddata?username=String&firstName=String&lastName=String

        User user = repository.findByUsername(username);

        try {

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCompanyName(companyName);

            repository.deleteByUsername(username);
            repository.save(user);

            return new ResponseEntity<>(new ErrorDescription("Added"), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("User not found"), HttpStatus.NOT_FOUND);
        }
    }
}
