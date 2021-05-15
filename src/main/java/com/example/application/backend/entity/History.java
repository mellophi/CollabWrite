package com.example.application.backend.entity;

import com.example.application.backend.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "history")
public class History extends AbstractEntity {

    private LocalDate postDate;
    private LocalDate updatedPostDate = LocalDate.now();
    private String updatedPost;
    private int userId;
    private int postId;


    public History() {
    }

    public History(LocalDate postDate, String updatedPost, int userId, int postId) {
        this.postDate = postDate;
        this.updatedPost = updatedPost;
        this.userId = userId;
        this.postId = postId;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public LocalDate getUpdatedPostDate() {
        return updatedPostDate;
    }
    public int getPostId() {
        return postId;
    }



    public void setPostId(int postId) {
        this.postId = postId;
    }


    public void setUpdatedPostDate(LocalDate updatedPostDate) {
        this.updatedPostDate = updatedPostDate;
    }

    public String getUpdatedPost() {
        return updatedPost;
    }

    public void setUpdatedPost(String updatedPost) {
        this.updatedPost = updatedPost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
