package com.example.application.backend.service;

import com.example.application.backend.entity.History;
import com.example.application.backend.entity.User;
import com.example.application.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void register(String username, String password, boolean upvoted  ) {
        userRepository.save(new User(username, password, upvoted));
    }

    public String findByUserID(int userid){
        return userRepository.findById(userid).get().getUsername();
    }

    public boolean isUpvoted(int userId) {
        return userRepository.findById(userId).get().isUpvoted();
    }

    public void updateUser(int user_id) {
        User user = userRepository.findById(user_id).get();
        user.setUpvoted(true);
        userRepository.save(user);
    }


    public List<User> findAllUsers() {
        return  userRepository.findAll();
    }
}
