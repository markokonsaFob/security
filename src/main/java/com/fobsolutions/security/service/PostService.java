package com.fobsolutions.security.service;

import com.fobsolutions.security.dao.PostRepository;
import com.fobsolutions.security.dao.UserRepository;
import com.fobsolutions.security.models.Comment;
import com.fobsolutions.security.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FOB Solutions
 */

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    public void addPost(String title, String subtitle, String content) {
        repository.save(new Post(title, subtitle, content, userRepository.findByUsername("FOB Solutions")));
    }

    public Post getPost(String id) {
        return repository.findById(id);
    }

    public void reset() {
        repository.deleteAll();
    }

    public void comment(String name, String comment, String postId) {
        Post post = repository.findById(postId);
        post.getComments().add(new Comment(name, comment));
        repository.save(post);
    }

    public List<Post> getPosts() {
        return repository.findAll();
    }

    public void addFirstPost() {

        if (repository.findAll().isEmpty()) {
            Post post = new Post("Security testing basics",
                    "This post gives you understanding which security vulnerabilities you can find here",
                    "On this very first project we would like to cover 3 topics from ASVS standard." +
                            " There are more security flaws than we planned but it's okay." +
                            " You can earn bonus points! " +
                            "The topics are: Session management, Malicious input, Business logic.", userRepository.findByUsername("FOB Solutions"));
            repository.save(post);
        }
    }
}
