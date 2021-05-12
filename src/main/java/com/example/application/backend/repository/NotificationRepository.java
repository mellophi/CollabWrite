package com.example.application.backend.repository;

import com.example.application.backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    List<Notification> findAllByNotifiedUserId(int userId);
}
