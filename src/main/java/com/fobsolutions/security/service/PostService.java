package com.fobsolutions.security.service;

import com.fobsolutions.security.dao.PostRepository;
import com.fobsolutions.security.dao.UserRepository;
import com.fobsolutions.security.models.Comment;
import com.fobsolutions.security.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
            Post intro = new Post("Security testing basics",
                    "This post gives you understanding of the security vulnerabilities you can find here",
                    "In this first project we would like to cover 2 topics from ASVS standard." +
                            " There are more bugs than we planned but it's okay." +
                            " You can earn bonus points! " +
                            "The topics are: Session management, Malicious input",
                    userRepository.findByUsername("FOB Solutions"));

            Post sessionManagement = new Post("Session management basics",
                    "Every web application uses sessions, one way or another.",
                    "There are multiple security flaws in this project regarding to session management." +
                            " Try to understand how sessions are used in this project, can you manipulate with them." +
                            " If none of the above leads you nowhere then you can read more about session management from" +
                            " https://www.owasp.org/index.php/Session_Management_Cheat_Sheet",
                    userRepository.findByUsername("FOB Solutions"));

            Post maliciousInput = new Post("Malicious input/Data validation basics",
                    "Data should be validated both ways (server <-> client).",
                    "The most common web application security weakness is the failure to properly validate input from the client or environment." +
                            " This weakness leads to almost all of the major vulnerabilities in applications," +
                            " such as Interpreter Injection," +
                            " locale/Unicode attacks," +
                            " file system attacks and buffer overflows." +
                            " Data from the client should never be trusted for the client has every possibility to tamper with the data.",
                    userRepository.findByUsername("FOB Solutions"));

            repository.save(Arrays.asList(intro, sessionManagement, maliciousInput));
        }
    }
}
