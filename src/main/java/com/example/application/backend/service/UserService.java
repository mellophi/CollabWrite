package com.example.application.backend.service;

import com.example.application.backend.entity.User;
import com.example.application.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String username, String password) {
        userRepository.save(new User(username, password));
    }

    public String FindByUserID(int userid){
        return userRepository.findById(userid).get().getUsername();
    }
}
