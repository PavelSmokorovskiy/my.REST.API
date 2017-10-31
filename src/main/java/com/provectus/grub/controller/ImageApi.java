package com.provectus.grub.controller;

import com.provectus.grub.model.ErrorDescription;
import com.provectus.grub.model.Image;
import com.provectus.grub.persistence.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/images")
@ResponseStatus(HttpStatus.OK)
public class ImageApi {

    @Autowired
    private ImageRepository repository;


    @GetMapping(value = "/all")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    List<Image> findAll() {

        //$ curl 'localhost:8080/api/images/all'

        return repository.findAll();
    }

    @GetMapping(value = "/byname")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    ResponseEntity findByName(@RequestParam String name) {

        //$ curl 'localhost:8080/api/images/byname?name=String'

        Image image = repository.findByName(name);

        try {

            if (image.getName().equals(name)) {

                return new ResponseEntity<>(image, HttpStatus.OK);

            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Image does not exist"), HttpStatus.NOT_FOUND);

        }

        return null;
    }

    @PostMapping(value = "/new")
    @ResponseBody
//    @ResponseStatus(value = HttpStatus.CREATED, reason = "Image saved")
    ResponseEntity newImage(@RequestParam String name, @RequestParam String url) {

        //$ curl 'localhost:8080/api/images/new?name=String&url=String'

        Image tmpImage = repository.findByName(name);

        try {

            if (tmpImage.getName().equals(name)) {

                return new ResponseEntity<>(new ErrorDescription("Image already exist"), HttpStatus.NOT_ACCEPTABLE);

            }

        } catch (Exception e) {

            if ((url!="")&&(url!=null)){

                Image image = new Image(name, url);
                repository.save(image);

                return new ResponseEntity<>(new ErrorDescription("Image saved"), HttpStatus.OK);

            } else {

                return new ResponseEntity<>(new ErrorDescription("Url is not specified"), HttpStatus.NOT_ACCEPTABLE);

            }
        }

        return null;
    }

    @DeleteMapping(value = "/delimage")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    ResponseEntity delImage(@RequestParam String name) {

        //$ curl 'localhost:8080/api/images/delimage?name=String'

        Image tmpImage = repository.findByName(name);

        try {

            if (tmpImage.getName().equals(name)) {

                repository.deleteByName(name);
                return new ResponseEntity<>(new ErrorDescription("Image deleted"), HttpStatus.OK);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Image does not exist"), HttpStatus.NOT_ACCEPTABLE);
        }

        return null;
    }
}
