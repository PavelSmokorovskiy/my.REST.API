package com.provectus.grub.persistence;

import com.provectus.grub.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    int countAllBy();
    Order findById(int id);
    void deleteById(int id);

}
