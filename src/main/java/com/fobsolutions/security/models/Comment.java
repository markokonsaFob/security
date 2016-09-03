package com.fobsolutions.security.models;

import org.springframework.data.annotation.Id;

/**
 * Created by FOB Solutions
 */
public class Comment {

    @Id
    String id;
    String author;
    String comment;

    public Comment(String author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
