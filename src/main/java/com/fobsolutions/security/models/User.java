package com.fobsolutions.security.models;

import org.springframework.data.annotation.Id;

/**
 * Created by FOB Solutions
 */
public class User {

    @Id
    public String id;
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
