package com.example.application.backend.service;

import com.example.application.backend.entity.Notification;
import com.example.application.backend.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public  void saveNotification(String text,int notifiedUserId,int notifiedByUserId,int postId)
    {
        notificationRepository.save(new Notification(text,notifiedUserId,notifiedByUserId,postId));
    }

    public List<Notification> findAllByNotifiedUserId(int userId) {
        return notificationRepository.findAllByNotifiedUserId(userId);
    }

    public void deleteNotification(Notification notification) {
       notificationRepository.delete(notification);
    }
}
