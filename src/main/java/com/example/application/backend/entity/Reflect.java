package com.example.application.backend.entity;

import com.example.application.backend.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reflect")
public class Reflect extends AbstractEntity {

    private String post;
    private int user_id;

    public Reflect() {
    }

    public Reflect(String post, int user_id) {
        this.post = post;
        this.user_id = user_id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
