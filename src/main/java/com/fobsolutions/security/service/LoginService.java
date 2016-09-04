package com.fobsolutions.security.service;

import com.fobsolutions.security.dao.UserRepository;
import com.fobsolutions.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by FOB Solution
 */

@Service
public class LoginService {

    @Autowired
    private UserRepository repository;

    public void addAdminUser() {

        if (repository.findByUsername("FOB Solutions") == null) {
            repository.save(new User("FOB Solutions", "Admin"));
        }
    }

    public User login(String username, String password) {
        User user = repository.findByUsername(username);
        if (user != null && user.password.equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public User register(String username, String password, String password2) {
        User user = null;
        if (password.equals(password2)) {
            user = new User(username, password);
            user = repository.save(user);
        }
        return user;
    }

    public void reset() {
        List<User> users = repository.findAll();

        users.stream().filter(user -> !user.username.equals("FOB Solutions")).forEach(user -> {
            repository.delete(user);
        });
    }

    public HttpServletResponse logout(Cookie[] cookies, HttpServletResponse response) {

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                cookie.setValue("");
            }
            response.addCookie(cookie);
        }
        return response;
    }

    public boolean isLoggedIn(Cookie[] cookies) {
        boolean isLoggedIn = false;
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    User user = repository.findById(cookie.getValue());
                    if (user != null) isLoggedIn = true;
                    break;
                }
            }
        } catch (Exception ignored) {

        }

        return isLoggedIn;
    }

    public User getUserFromCookie(Cookie[] cookies) {
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    return repository.findById(cookie.getValue());
                }
            }
        } catch (Exception ignored) {
        }

        return null;
    }
}
