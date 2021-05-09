package com.example.application.backend.entity;

import com.example.application.backend.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reflect")
public class Reflect extends AbstractEntity {

    private String post;
    private int user_id;
    @Column(name = "post_date")
    private LocalDate postDate = LocalDate.now();
    private int latest_user_id;



    public Reflect() {
    }

    public Reflect(String post, int user_id, int latest_user_id) {
        this.post = post;
        this.user_id = user_id;
        this.latest_user_id = latest_user_id;
    }


    public int getUser_id() {
        return user_id;
    }
    public String getPost() {
        return post;
    }
    public LocalDate getPostDate() {
        return postDate;
    }
    public int getLatest_user_id() { return latest_user_id; }



    public void setLatest_user_id(int latest_user_id) { this.latest_user_id = latest_user_id; }
    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
