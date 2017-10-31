package com.provectus.grub.controller;

import com.provectus.grub.model.ErrorDescription;
import com.provectus.grub.model.User;
import com.provectus.grub.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserApi {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {

        //$ curl 'localhost:8080/api/users/all?access_token=String'

        return repository.findAll();
    }

    @RequestMapping(value = "/byusername", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Data updated")
    ResponseEntity findByName(@RequestParam String username) {

        //$ curl 'localhost:8080/api/users/byusername?username=String

        User user = repository.findByUsername(username);

        if (user == null) {

            return new ResponseEntity<>(new ErrorDescription("Username does not exist"), HttpStatus.NOT_FOUND);

        }

        if (user.getUsername().equals(username)) {

            return new ResponseEntity<>(user, HttpStatus.OK);

        }

        return null;
    }

    @RequestMapping(value = "/deluser", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity delUser(@RequestParam String username) {

        //$ curl 'localhost:8080/api/users/deluser

        User user = repository.findByUsername(username);

        try {

            if (user.getUsername().equals(username)) {

                repository.deleteByUsername(username);
                return new ResponseEntity<>(new ErrorDescription("User deleted"), HttpStatus.OK);

            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Username does not exist"), HttpStatus.NOT_FOUND);

        }

        return null;
    }
}
