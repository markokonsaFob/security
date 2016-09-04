package com.fobsolutions.security.controllers;

import com.fobsolutions.security.models.Post;
import com.fobsolutions.security.service.LoginService;
import com.fobsolutions.security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FOB Solutions
 */
@Controller
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserController userController;

    @RequestMapping("/blog")
    public String blogMainPage(Model model, HttpServletRequest request) {

        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        model.addAttribute("posts", service.getPosts());
        return "blog/index";
    }

    @GetMapping("/post")
    public String blogPost(
            @RequestParam(value = "id", required = true) String id,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        model.addAttribute("post", service.getPost(id));
        return "blog/post";
    }

    @PostMapping("/post")
    public String newPost(
            @RequestParam(value = "title", required = true) String name,
            @RequestParam(value = "subtitle", required = false, defaultValue = "") String subtitle,
            @RequestParam(value = "postContent", required = true) String content,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));

        if (loginService.isLoggedIn(request.getCookies())) {
            service.addPost(name, subtitle, content);
            return blogMainPage(model, request);
        } else {
            return new UserController().loginpage(model, request);
        }
    }

    @RequestMapping("/new")
    public String newPostPage(HttpServletRequest request, Model model) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));

        if (loginService.isLoggedIn(request.getCookies())) {
            return "blog/new";
        } else {
            return userController.loginpage(model, request);
        }
    }

    @GetMapping("/comments")
    public String commentPage(
            @RequestParam(value = "postId", required = true) String postId,
            Model model,
            HttpServletRequest request
    ) {
        Post post = service.getPost(postId);
        boolean isLoggedIn = loginService.isLoggedIn(request.getCookies());
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            model.addAttribute("currentUser", loginService.getUserFromCookie(request.getCookies()));
        }

        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("comments", post.getComments());
            return "blog/comments";
        } else {
            return blogMainPage(model, request);
        }
    }


    @PostMapping("/comment")
    public String addComment(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "comment", required = true) String comment,
            @RequestParam(value = "postId", required = true) String postId,
            Model model,
            HttpServletRequest request
    ) {
        if (loginService.isLoggedIn(request.getCookies())) {
            service.comment(name, comment, postId);
            return commentPage(postId, model, request);
        } else {
            return userController.loginpage(model, request);
        }
    }

}
