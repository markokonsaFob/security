package com.fobsolutions.security.dao;

import com.fobsolutions.security.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by FOB Solutions
 */
public interface PostRepository extends MongoRepository<Post, String> {

    public Post findById(String id);

}
