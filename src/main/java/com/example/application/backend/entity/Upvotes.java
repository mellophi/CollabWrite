package com.example.application.backend.entity;

import com.example.application.backend.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "upvotes")
public class Upvotes extends AbstractEntity {
    private int userId;
    private int postId;

    public Upvotes() {
    }

    public Upvotes(int postId, int userId) {
        this.userId = userId;
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
