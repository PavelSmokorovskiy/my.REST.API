package com.provectus.grub.controller;


import com.provectus.grub.model.ErrorDescription;
import com.provectus.grub.model.User;
import com.provectus.grub.persistence.UserRepository;
import com.provectus.grub.service.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmailActivation {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailNotification emailNotification;

    @RequestMapping(value = "/activation", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity activation(@RequestParam String activationCode) {

        //$ curl 'grub.com.ua:8080/activation?activationCode=String

        try {

            User user = repository.findByActivationCode(activationCode);
            user.setEmailConfirmation(true);
            repository.save(user);

        } catch (NullPointerException e) {

            return new ResponseEntity<>(new ErrorDescription("Activation code not found"), HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(new ErrorDescription("Email confirmed"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sendActivationCode", method = RequestMethod.POST)
    public ResponseEntity sendActivationCode(@RequestParam String username) {

        //$ curl 'localhost:8080/sendActivationCode?username=String

        try {

            emailNotification.sendNotification(repository.findByUsername(username));

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("User created. Email not found"), HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(new ErrorDescription("User created. Activation code sent"), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/sendPasswordReminder", method = RequestMethod.POST)
    public ResponseEntity sendPasswordReminder(@RequestParam String username) {

        //$ curl 'localhost:8080/sendPasswordReminder?username=String

        try {

            emailNotification.sendPasswordReminder(repository.findByUsername(username));

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Email not found"), HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(new ErrorDescription("Password reminder sent"), HttpStatus.OK);

    }
}
