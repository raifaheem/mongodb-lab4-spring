package com.lab4.lab4database.userrepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lab4.lab4database.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

}
