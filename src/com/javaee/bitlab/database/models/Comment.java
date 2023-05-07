package com.javaee.bitlab.database.models;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private String comment;
    private User user;
    private News news;
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Comment(Long id, String comment, User user, News news) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.news = news;
    }

    public Comment(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
