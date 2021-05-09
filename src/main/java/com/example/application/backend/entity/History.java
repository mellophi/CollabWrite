package com.example.application.backend.entity;

import com.example.application.backend.AbstractEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class History extends AbstractEntity {

    private LocalDate post_date;
    private LocalDate update_post_date = LocalDate.now();
    private String update_post;
    private int user_id;

    public History() {
    }

    public History(LocalDate post_date, String update_post, int user_id) {
        this.post_date = post_date;
        this.update_post = update_post;
        this.user_id = user_id;
    }

    public LocalDate getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDate post_date) {
        this.post_date = post_date;
    }

    public LocalDate getUpdate_post_date() {
        return update_post_date;
    }

    public void setUpdate_post_date(LocalDate update_post_date) {
        this.update_post_date = update_post_date;
    }

    public String getUpdate_post() {
        return update_post;
    }

    public void setUpdate_post(String update_post) {
        this.update_post = update_post;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
