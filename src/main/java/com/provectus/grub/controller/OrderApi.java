package com.provectus.grub.controller;

import com.provectus.grub.model.*;
import com.provectus.grub.persistence.FoodRepository;
import com.provectus.grub.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApi {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping(value = "/all")
    @ResponseBody
//    @ResponseStatus(value = HttpStatus.OK)
    List<Order> findAll() {

        //$ curl 'localhost:8080/api/order/all'

        return repository.findAll();
    }

    @GetMapping(value = "/byid")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    ResponseEntity findById(@RequestParam int id) {

        //$ curl 'localhost:8080/api/order/byid?id=String'

        try {

            if (repository.findById(id).getId() == id) {

                return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);

            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Order does not exist"), HttpStatus.NOT_FOUND);

        }

        return null;
    }

    @PostMapping(value = "/new")
    @ResponseBody
//    @ResponseStatus(value = HttpStatus.CREATED, reason = "Order saved")
    ResponseEntity newOrder(
            User username
            , String userAdmin
            , Food foodName
            , Status status
            , String lockTime
            , String orderTime
            , String comment
    ) throws ParseException {

        //$ curl 'localhost:8080/api/order/new?username=email@mail.com&foodName=String&status=DRAFT&date=31.12.17-23:59&comment=String'

        Order order = Order.builder()
                .id(repository.countAllBy() + 1)
                .userAdmin(userAdmin)
                .users(username, foodName)
                .status(status)
                .lockTime(new SimpleDateFormat("yy/MM/dd-HH:mm").parse(lockTime))
                .orderTime(new SimpleDateFormat("yy/MM/dd-HH:mm").parse(orderTime))
                .comment(comment)
                .build();
        foodName.setId(foodRepository.countAllBy()+1); //temporary solution
        repository.save(order);

            return new ResponseEntity<>(new ErrorDescription("Order saved"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/del")
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    ResponseEntity delOrder(@RequestParam int id) {

        //$ curl 'localhost:8080/api/order/del?id=String'

        try {

            if (repository.findById(id).getId() == id) {

                repository.deleteById(id);
                return new ResponseEntity<>(new ErrorDescription("Order deleted"), HttpStatus.OK);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDescription("Order does not exist"), HttpStatus.NOT_ACCEPTABLE);
        }

        return null;
    }
}
