package com.example.application.backend.repository;

import com.example.application.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

}
