package com.provectus.grub.persistence;

import com.provectus.grub.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {

    int countAllBy();
    Food findByFoodName(String foodName);
    void deleteByFoodName(String name);
    Food findById(int id);


}
