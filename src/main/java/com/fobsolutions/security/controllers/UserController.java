package com.fobsolutions.security.controllers;

import com.fobsolutions.security.models.User;
import com.fobsolutions.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by FOB Solutions
 */

@Controller
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PostController postController;

    @GetMapping("/login")
    public String loginpage(Model model, HttpServletRequest request) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        model.addAttribute("usernameError", loginService.isLoggedIn(request.getCookies()));
        model.addAttribute("passwordError", loginService.isLoggedIn(request.getCookies()));
        return "blog/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model, HttpServletResponse response) {
        loginService.logout(request.getCookies(), response);
        return postController.blogMainPage(model, request);
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpServletResponse response,
            HttpServletRequest request,
            Model model
    ) {
        User user = loginService.login(username, password);
        if (user != null) {
            response.addCookie(new Cookie("userId", user.id));
            model.addAttribute("isLoggedIn", true);
            return postController.blogMainPage(model, request);
        } else {
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("error", "Login incorrect!");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return loginpage(model, request);
        }
    }

    @PostMapping("/register")
    public String register(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "password2", required = true) String password2,
            HttpServletResponse response,
            HttpServletRequest request,
            Model model
    ) {
        User user = loginService.register(username, password, password2);
        if (user != null) {
            response.addCookie(new Cookie("userId", user.id));
            model.addAttribute("isLoggedIn", true);
            return postController.blogMainPage(model, request);
        } else {
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("error", "Incorrect!");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return loginpage(model, request);
        }
    }


}
