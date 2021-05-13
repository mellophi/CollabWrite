package com.example.application.backend.service;

import com.example.application.backend.repository.FriendRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    private FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }
}
