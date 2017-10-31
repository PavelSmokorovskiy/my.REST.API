package com.provectus.grub.persistence;

import com.provectus.grub.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    int countAllBy();
    User findByUsername(String username);
    void deleteByUsername(String username);
    User findByActivationCode(String activationCode);

}
