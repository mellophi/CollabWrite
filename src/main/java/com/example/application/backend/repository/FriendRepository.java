package com.example.application.backend.repository;

import com.example.application.backend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Integer> {

}
