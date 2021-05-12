package com.example.application.backend.entity;

import com.example.application.backend.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Notification extends AbstractEntity {
    private String text;
    private int notifiedUserId;
    private int notifiedByUserId;
    private int postId;

    public Notification() {
    }

    public Notification(String text, int notifiedUserId, int notifiedByUserId, int postId) {
        this.text = text;
        this.notifiedUserId = notifiedUserId;
        this.notifiedByUserId = notifiedByUserId;
        this.postId = postId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNotifiedUserId(int notifiedUserId) {
        this.notifiedUserId = notifiedUserId;
    }

    public void setNotifiedByUserId(int notifiedByUserId) {
        this.notifiedByUserId = notifiedByUserId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public int getNotifiedUserId() {
        return notifiedUserId;
    }

    public int getNotifiedByUserId() {
        return notifiedByUserId;
    }

    public int getPostId() {
        return postId;
    }
}
