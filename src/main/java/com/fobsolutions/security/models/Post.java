package com.fobsolutions.security.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FOB Solutions
 */
public class Post {

    @Id
    public String id;
    private String title;
    private String subtitle;
    private String content;
    private User user;
    private List<Comment> comments;
    private LocalDate createdAt;

    public Post(String title, String subtitle, String content, User user) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.user = user;
        this.createdAt = LocalDate.now();
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
