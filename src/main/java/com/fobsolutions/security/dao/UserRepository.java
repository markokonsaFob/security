package com.fobsolutions.security.dao;

import com.fobsolutions.security.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by FOB Solutions
 */
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(String username);

    public User findById(String id);

}
