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
        int userId = reflectService.fetchUserId(username);
        List<Friend> friends = friendRepository.findAll();
        for(Friend friend : friends){
            if((friend.getFriendId() == friendId && friend.getUserId() == userId) || (friend.getUserId() == friendId && friend.getFriendId() == userId)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void addFriend(int notifiedUserId, int notifiedByUserId) {
        friendRepository.save(new Friend(notifiedUserId,notifiedByUserId));
    }
}
