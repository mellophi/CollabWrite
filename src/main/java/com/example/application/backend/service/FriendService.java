package com.example.application.backend.service;

import com.example.application.backend.entity.Friend;
import com.example.application.backend.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {
    private FriendRepository friendRepository;
    private ReflectService reflectService;
    private UserService userService;

    public FriendService(FriendRepository friendRepository, ReflectService reflectService, UserService userService) {
        this.friendRepository = friendRepository;
        this.reflectService = reflectService;
        this.userService = userService;
    }

    public List<String> findFriends(String username) {
        List<Friend> friends = friendRepository.findAllByUserId(reflectService.fetchUserId(username));
        List<String> friendName = new ArrayList<String>();
        for(Friend friend : friends){
            friendName.add(userService.findByUserID(friend.getFriendId()));
        }
        return friendName;
    }

    public boolean checkFriends(String username, int friendId) {
        boolean flag = false;
        List<Friend> friends = friendRepository.findAllByUserId(reflectService.fetchUserId(username));
        for(Friend friend : friends){
            if(friend.getFriendId() == friendId){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
