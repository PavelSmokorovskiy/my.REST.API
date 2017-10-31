package com.provectus.grub.persistence;

import com.provectus.grub.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    int countAllBy();
    Image findByName(String name);
    void deleteByName(String name);
    Image findById(int id);

}
