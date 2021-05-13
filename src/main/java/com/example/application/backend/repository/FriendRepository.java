package com.example.application.backend.repository;

import com.example.application.backend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Integer> {

    List<Friend> findAllByUserId(int userId);
}
