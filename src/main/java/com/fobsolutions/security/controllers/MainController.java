package com.fobsolutions.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by FOB Solutions
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public String landingPage() {
        return "index";
    }

    @RequestMapping("/blog")
    public String blogMainPage() {
        return "blog/index";
    }

    @GetMapping("/post")
    public String blogPost(
            @RequestParam(value = "id", required = true) int id
    ) {
        return "blog/post";
    }

    @PostMapping("/post")
    public String newPostPage(
            @RequestParam(value = "title", required = true) String name,
            @RequestParam(value = "subtitle", required = false, defaultValue = "") String subtitle,
            @RequestParam(value = "postContent", required = true) String content
    ) {
        return blogMainPage();
    }

    @RequestMapping("/new")
    public String newPost() {
        return "blog/new";
    }

    @GetMapping("/login")
    public String loginpage() {
        return "blog/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        return "blog/index";
    }

    @RequestMapping("/donate")
    public String donatePage() {
        return "blog/donate";
    }


    @PostMapping("/donate")
    public String addDonation(
            @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name,
            @RequestParam(value = "amount", required = true) int amount
    ) {
        return "blog/index";
    }

    @RequestMapping("/comment")
    public String commentPage() {
        return "blog/comment";
    }


    @PostMapping("/comment")
    public String addComment(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "comment", required = true) String comment
    ) {
        return "blog/index";
    }
}
