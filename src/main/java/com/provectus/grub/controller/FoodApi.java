package com.provectus.grub.controller;

import com.provectus.grub.model.*;
import com.provectus.grub.persistence.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/food")
public class FoodApi {

    @Autowired
    private FoodRepository repository;

    @GetMapping(value = "/all")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    List<Food> findAll() {

        //$ curl 'localhost:8080/api/food/all'

        return repository.findAll();
    }

    @GetMapping(value = "/byname")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    ResponseEntity findByName(@RequestParam String foodName) {

        //$ curl 'localhost:8080/api/food/byname?foodName=String'

        Food food = repository.findByFoodName(foodName);

        try {

            if (food.getFoodName().equals(foodName)) {

                return new ResponseEntity<>(food, HttpStatus.OK);

            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Image does not exist"), HttpStatus.NOT_FOUND);

        }

        return null;
    }

    @PostMapping(value = "/new")
    @ResponseBody
//    @ResponseStatus(value = HttpStatus.CREATED, reason = "Food saved")
    ResponseEntity newFood(
            String foodName
            , String description
            , Category category
            , Image imageUrl
            , Status status
            , int rating
            , double price
            , int amount) {

        //$ curl 'localhost:8080/api/food/new?foodName=String&description=String&category=PIZZA&image=String&status=DRAFT&rating=1&price=1&amount=1'

        try {

            if (repository.findByFoodName(foodName).getFoodName().equals(foodName)) {

                return new ResponseEntity<>(new ErrorDescription("Food already exist"), HttpStatus.NOT_ACCEPTABLE);

            }

        } catch (Exception e) {

            if (category != null) {

                Food food = Food.builder()
                        .id(repository.countAllBy() + 1)
                        .foodName(foodName)
                        .description(description)
                        .category(category)
                        .image(imageUrl)
                        .status(status)
                        .rating(rating)
                        .price(price)
                        .amount(amount)
                        .build();
                repository.save(food);

                return new ResponseEntity<>(new ErrorDescription("Food saved"), HttpStatus.OK);

            } else {

                return new ResponseEntity<>(new ErrorDescription("String is not specified"), HttpStatus.NOT_ACCEPTABLE);

            }
        }

        return null;
    }

    @DeleteMapping(value = "/del")
    @ResponseBody
    ResponseEntity delFood(@RequestParam String foodName) {

        //$ curl 'localhost:8080/api/food/del?foodName=String'

        Food tmpFood = repository.findByFoodName(foodName);

        try {

            if (tmpFood.getFoodName().equals(foodName)) {

                repository.findByFoodName(foodName);
                return new ResponseEntity<>(new ErrorDescription("Food deleted"), HttpStatus.OK);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Food does not exist"), HttpStatus.NOT_ACCEPTABLE);
        }

        return null;
    }


}
